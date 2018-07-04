package com.easy.work.address.service;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.work.address.model.CityModel;
import com.easy.work.address.model.ProvinceModel;

import java.util.List;

/**
 * Created by apple on 18/6/15.
 */
public interface AddressService {

    /**
     * 查询所有省份
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<ProvinceModel> searchProvinceList() throws MessageException,TransformException;

    /**
     * 根据省份id查找市区
     * @param provinceId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    public List<CityModel> searchCityList(String provinceId) throws MessageException,TransformException;
}
