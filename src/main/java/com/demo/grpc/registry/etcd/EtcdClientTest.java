package com.demo.grpc.registry.etcd;


import com.google.protobuf.ByteString;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Watch;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EtcdClientTest {

    @Resource
    private Watch watch;


    public void watchProcess(){
//        ByteString byteString = new ByteString("test");
//        byteString.
//        ByteSequence key = new ByteSequence();
//        watch.watch()
    }



}
