package br.edu.ifpb.bdnc.conexao;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class Redis {

    private Jedis jedis;

    public Jedis conectar(){
        jedis = new Jedis("localhost", 6379);
        return jedis;
    }
}
