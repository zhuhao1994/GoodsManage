package com.rainbow.service;

import com.rainbow.entity.Items;
import com.rainbow.entity.ItemsCustomer;
import com.rainbow.entity.ItemsQueryVo;

import java.util.List;

/**
 * Created by rainbow on 2016/6/27.
 * 一事专注，便是动人；一生坚守，便是深邃！
 */
public interface ItemsService {
    //查询商品列表
    public List<ItemsCustomer> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
    //根据ID查询商品信息
    public ItemsCustomer findById(Integer id)throws Exception;

    /**
     *
     * @param id 修改商品信息的id
     * @param itemsCustomer 要修改的商品信息
     * @throws Exception
     */
    public void updateItems(Integer id, ItemsCustomer itemsCustomer)throws Exception;

    public void insertItems(Items items)throws Exception;

    //根据id删除商品
    public void deleteById(Integer id)throws Exception;

}
