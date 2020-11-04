etcd

raft 协议
服务端go语言实现
发布/订阅
分布式锁   每次修改数据后 Revision单调递增变化的  可以做分布式锁
CP
集群配置
支持http API  long polling

注册中心的负载均衡
1：http的连接可以用 7层的负载均衡 nginx实现
2：TCP 例如zookeeper TCP长连接可以用 4层的负载均衡侧率 （LVS 流量负载到VIP机器上） 比如四层的负载均衡，就是通过发布三层的IP地址（VIP），然后加四层的端口号，来决定哪些流量需要做负载均衡，对需要处理的流量进行NAT处理，转发至后台服务器，并记录下这个TCP或者UDP的流量是由哪台服务器处理的，后续这个连接的所有流量都同样转发到同一台服务器处理
3：通过lease和keepAlive实现 服务注册和下线。
4：etcd raft如何实现Linearizable Read  线性顺序读
5：raft 和 praoxy 都只能写主  选举期间 不能对外提供服务 所以是CP


列出集群全部节点
./etcdctl --endpoints=http://127.0.0.1:2379 member list -w table




HOST_1=10.240.0.17
HOST_2=10.240.0.18
HOST_3=10.240.0.19
ENDPOINTS=$HOST_1:2379,$HOST_2:2379,$HOST_3:2379

etcdctl --endpoints=$ENDPOINTS member list

etcdctl --endpoints=$ENDPOINTS put foo "Hello World!"

etcdctl --write-out=table --endpoints=$ENDPOINTS endpoint status

使用--endpoints=$ENDPOINTS  自动写入主节点




./etcd --listen-peer-urls=http://localhost:2382  --listen-client-urls=http://localhost:2381 --initial-advertise-peer-urls=http://localhost:2380 --advertise-client-urls=http://localhost:2379


./etcdctl member add slave --peer-urls="http://127.0.0.1:2382"  增加节点


./etcd --listen-peer-urls=http://localhost:2384  --listen-client-urls=http://localhost:2383 --initial-advertise-peer-urls=http://localhost:2380,http://localhost:2382 --advertise-client-urls=http://localhost:2379,http://localhost:2381

./etcdctl member add slave-2 --peer-urls="http://127.0.0.1:2384"  增加节点