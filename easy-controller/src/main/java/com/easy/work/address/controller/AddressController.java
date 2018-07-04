package com.easy.work.address.controller;

import com.easy.core.exception.MessageException;
import com.easy.core.exception.TransformException;
import com.easy.core.util.ResponseUtil;
import com.easy.work.address.model.CityModel;
import com.easy.work.address.model.ProvinceModel;
import com.easy.work.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by apple on 18/6/15.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查找省份
     * @return
     */
    @RequestMapping(value = "/getProvinceList", method = RequestMethod.GET)
    public ResponseEntity getProvinceList() throws MessageException,TransformException{
        List<ProvinceModel> list = addressService.searchProvinceList();
        return ResponseUtil.success(list);
    }

    /**
     * 根据省份编号查找市区
     * @param provinceId
     * @return
     * @throws MessageException
     * @throws TransformException
     */
    @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
    public ResponseEntity getCityList(String provinceId) throws MessageException,TransformException{
        List<CityModel> list = addressService.searchCityList(provinceId);
        return ResponseUtil.success(list);
    }

}
