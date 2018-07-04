package com.easy.work.address.service.impl;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.TransUtils;
import com.easy.work.address.model.CityModel;
import com.easy.work.address.model.ProvinceModel;
import com.easy.work.address.service.AddressService;
import com.easy.work.city.dao.CityDao;
import com.easy.work.city.domain.CityDomain;
import com.easy.work.province.dao.ProvinceDao;
import com.easy.work.province.domain.ProvinceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by apple on 18/6/15.
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    /**
     * 查询所有省份
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<ProvinceModel> searchProvinceList() throws MessageException, TransformException {
        ProvinceDomain provinceDomain = new ProvinceDomain();
        List<ProvinceDomain> list = provinceDao.findList(provinceDomain);
        return TransUtils.formatList(ProvinceModel.class,list);
    }

    /**
     * 根据省份id查找市区
     * @param provinceId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @Override
    public List<CityModel> searchCityList(String provinceId) throws MessageException, TransformException {
        CityDomain cityDomain = new CityDomain();
        cityDomain.setProvinceId(Long.valueOf(provinceId));
        List<CityDomain> list = cityDao.findListCondition(cityDomain);
        return TransUtils.formatList(CityModel.class,list);
    }
}
