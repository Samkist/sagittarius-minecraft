/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.storage.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedisProvider {

    private final RedissonClient redisson;

    public RedisProvider(String address, String password) {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                // use "rediss://" for SSL connection
                .setAddress(address)
                .setPassword(password);

        redisson = Redisson.create(config);
    }

    public RedissonClient client() {
        return redisson;
    }
}
