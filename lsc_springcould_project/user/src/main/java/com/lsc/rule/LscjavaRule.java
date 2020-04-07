package com.lsc.rule;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LscjavaRule extends AbstractLoadBalancerRule {
    Random random;
    private int nowIndex = -1;//这次下标
    private int lastIndex = -1;//上一次下标
    private int skipIndex = -1; //要跳过的下标(上一次下标等于这次下标 就把这次下标赋值给跳过的下标)
    public LscjavaRule(){
        random = new Random();
    }
    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            //可用的服务列表
            List<Server> upList = lb.getReachableServers();
            //所有的服务列表
            List<Server> allList = lb.getAllServers();
            //获取所有服务列表的数量
            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }
            int index = chooseRandomInt(serverCount);
            if (serverCount !=1){
                index = LscRandom(index,serverCount);
            }
            server = upList.get(index);
            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }
            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
        return server;
    }

    public int LscRandom(int index,int serverCount){
        //如果取出来的下标 是要跳过的下标
        if (index == skipIndex){
            System.out.println("跳过重新随机");
            index = chooseRandomInt(serverCount);
            if (index == skipIndex){
                index = LscRandom(index,serverCount);
            }
        }
        skipIndex = -1;//跳过后, 清空要跳过的下标
        nowIndex = index;//记录这次下标
        //如果这次下标等于上次下标
        if (lastIndex == nowIndex){
            skipIndex = nowIndex; //就把这次下标赋值给跳过的下标
            System.out.println("这次和上次的下标一样");
        }
        //随机完之后,这一次的下标就是上一次的下标
        lastIndex = nowIndex;
        return index;
    }



    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}
