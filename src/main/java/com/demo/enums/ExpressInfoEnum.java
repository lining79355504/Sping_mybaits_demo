package com.demo.enums;

/**
 * Created by suoer on 10/18/16.
 */
public enum ExpressInfoEnum {

    YUNDA(10 , "yunda" , "YUNDA", "韵达"),
    ZHONGTONG(12 , "zhongtong" , "ZTO", "中通"),
    SHENGTONG(4 , "shentong" , "STO", "申通"),
    SHUNFENG(8 , "shunfeng" , "SF", "顺风"),
    TIANTIAN(20 , "tiantian" , "TTKDEX", "天天快递"),
    HUITONG(18 , "huitongkuaidi" , "HTKY", "汇通快递"),
    EMS(3 , "ems" , "EMS", "EMS标准快递"),
    YUANTONG(1 , "yuantong" , "GTO", "圆通快递"),
    KUAIJIE(14 , "kuaijiesudi" , "FAST", "快捷快递"),
            ;



    private Integer expressId;
    private String expressCodeTrade;
    private String expressCodeWMS;
    private String expressName;

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        expressId = expressId;
    }

    public String getExpressCodeTrade() {
        return expressCodeTrade;
    }

    public void setExpressCodeTrade(String expressCodeTrade) {
        expressCodeTrade = expressCodeTrade;
    }

    public String getExpressCodeWMS() {
        return expressCodeWMS;
    }

    public void setExpressCodeWMS(String expressCodeWMS) {
        expressCodeWMS = expressCodeWMS;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        expressName = expressName;
    }

    ExpressInfoEnum(Integer expressId, String expressCodeTrade, String expressCodeWMS, String expressName){
        this.expressId = expressId;
        this.expressCodeTrade = expressCodeTrade;
        this.expressCodeWMS = expressCodeWMS;
        this.expressName = expressName;

    }


    public static ExpressInfoEnum getExpressByExpressId(Integer expressId){

        for (ExpressInfoEnum expressInfoEnum:ExpressInfoEnum.values()
             ) {
            if(expressInfoEnum.getExpressId().equals(expressId)){
                return expressInfoEnum;
            }
            
        }
        return null ;
    }


    public static ExpressInfoEnum getExpressByExpressCodeTrade(String expressCodeTrade){

        for (ExpressInfoEnum expressInfoEnum:ExpressInfoEnum.values()
                ) {
            if(expressInfoEnum.getExpressCodeTrade().equals(expressCodeTrade)){
                return expressInfoEnum;
            }

        }
        return null ;

    }

    public static ExpressInfoEnum getExpressByExpressCodeWMS(String expressCodeWMS){

        for (ExpressInfoEnum expressInfoEnum:ExpressInfoEnum.values()
                ) {
            if(expressInfoEnum.getExpressCodeWMS().equals(expressCodeWMS)){
                return expressInfoEnum;
            }

        }
        return null ;

    }










}
