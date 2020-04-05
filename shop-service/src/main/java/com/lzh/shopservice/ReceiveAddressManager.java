package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopcommon.util1.UID;
import com.lzh.shop.dao.ReceiveAddressDao;
import com.lzh.shopentity.ReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址维护
 */
@Service
public class ReceiveAddressManager {
    @Autowired
    private ReceiveAddressDao receiveAddressDao;

    public ReceiveAddress getReceiveAddress(Long id) {
        return receiveAddressDao.get(id);
    }

    public List<ReceiveAddress> listReceiveAddress(Map<String, Object> filters) {
        return receiveAddressDao.findListForMap(filters);
    }
    public List<ReceiveAddress> findListForMapByPartyId(Map<String, Object> filters) {
        return receiveAddressDao.findListForMapByPartyId(filters);
    }

    public void insert(ReceiveAddress receiveAddress){
        receiveAddressDao.insert(receiveAddress);
    }

    /**
     * 设置默认地址
     *
     * @param receiveAddressId
     * @return
     */
    public boolean setDefaultAddress(Long receiveAddressId) {
        ReceiveAddress newReceiveAddress = getReceiveAddress(receiveAddressId);

        try {
            setAddressUnDefault(newReceiveAddress.getUserId());
            newReceiveAddress.setDefaultAddress(true);
            receiveAddressDao.update(newReceiveAddress);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setAddressUnDefault(long userId) {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("defaultAddress", true);
        filters.put("userId", userId);
        List<ReceiveAddress> receiveAddressList = listReceiveAddress(filters);
        if (receiveAddressList.size() > 0) {
            ReceiveAddress oldReceiveAddress = receiveAddressList.get(0);
            oldReceiveAddress.setDefaultAddress(false);
            receiveAddressDao.update(oldReceiveAddress);
        }
    }

    public void saveOrUpdate(ReceiveAddress receiveAddress) {
        //如果原来没有默认地址，则把新增的添加为默认地址，否则看添加或修改的地址 By QiHaiYang
        ReceiveAddress receiveAddress1Def = getDefaultAddressByParty(receiveAddress.getUserId());
        if(receiveAddress1Def == null){
            receiveAddress.setDefaultAddress(true);
        }else if(receiveAddress.isDefaultAddress()){
            receiveAddress1Def.setDefaultAddress(false);
            receiveAddressDao.update(receiveAddress1Def);
        }
    }


    public ReceiveAddress getDefaultAddressByParty(Long userId) {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("userId", userId);
        filters.put("defaultAddress", true);
        List<ReceiveAddress> receiveAddressList = listReceiveAddress(filters);
        if (receiveAddressList.size() > 0) {
            return receiveAddressList.get(0);
        }
        return null;
    }

    //新增并返回id
    public Long createAddress(ReceiveAddress receiveAddress) {
        ReceiveAddress receiveAddress1Def = getDefaultAddressByParty(receiveAddress.getUserId());
        if(receiveAddress1Def == null){
            receiveAddress.setDefaultAddress(true);
        } else if (receiveAddress.isDefaultAddress()) {
            receiveAddress1Def.setDefaultAddress(false);
            receiveAddressDao.update(receiveAddress1Def);
        }
        Long id = UID.next();
        //receiveAddress.setAreaId(party.getAreaId());
        receiveAddress.setId(id);
        receiveAddressDao.insert(receiveAddress);
        return id;
    }


    public int deleteReceiveAddress(long receiveAddressId) {
        return receiveAddressDao.delete(receiveAddressId) ;
    }

    public Page<ReceiveAddress> findAddressPage(Page<ReceiveAddress> page, Map<String, Object> map) {
        return receiveAddressDao.findPageForMap(page, map);
    }


    public ReceiveAddress getConInfo(long partyId) {
        Map<String,Object> filters = new HashMap<String,Object>();
        filters.put("partyId", partyId);
        filters.put("orderBy", "DEFAULT_ADDRESS");
        List<ReceiveAddress> myAddresses = this.listReceiveAddress(filters);
        for (ReceiveAddress receiveAddress : myAddresses) {
            if (receiveAddress.isDefaultAddress()) {
                return receiveAddress;
            }
        }
        return null;
    }
}