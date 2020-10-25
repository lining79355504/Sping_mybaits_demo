package com.demo.cacheDo;

public class  CacheDO<T>{


        private T object ;

        private long time ;

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "CacheDO{" +
                    "object=" + object +
                    ", time=" + time +
                    '}';
        }
    }
