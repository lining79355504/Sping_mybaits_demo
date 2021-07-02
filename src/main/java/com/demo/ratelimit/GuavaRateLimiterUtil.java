package com.demo.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mort
 * @Description
 * @date 2021/7/2
 **/
public class GuavaRateLimiterUtil {

    private static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<String, RateLimiter>();

    public static RateLimiter createResourceRateLimiter(String resource, double qps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource, rateLimiter);
        }

        return resourceRateLimiter.get(resource);

    }

}
