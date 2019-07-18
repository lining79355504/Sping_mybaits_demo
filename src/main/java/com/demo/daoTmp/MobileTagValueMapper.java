package com.demo.daoTmp;

import com.demo.entityTmp.MobileTagValue;
import com.demo.entityTmp.MobileTagValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MobileTagValueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int deleteByExample(MobileTagValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int insert(MobileTagValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int insertSelective(MobileTagValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    List<MobileTagValue> selectByExample(MobileTagValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    MobileTagValue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") MobileTagValue record, @Param("example") MobileTagValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int updateByExample(@Param("record") MobileTagValue record, @Param("example") MobileTagValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int updateByPrimaryKeySelective(MobileTagValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mapi_config
     *
     * @mbg.generated Wed Jul 17 17:07:52 CST 2019
     */
    int updateByPrimaryKey(MobileTagValue record);
}