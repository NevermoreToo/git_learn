package com.atguigu;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import com.rfidread.Interface.IAsynchronousMessage;
import com.rfidread.Models.GPI_Model;
import com.rfidread.Models.Tag_Model;

public class IMessageHandler implements IAsynchronousMessage {
    @Override
    public void WriteDebugMsg(String s) {

    }

    @Override
    public void WriteLog(String s) {

    }

    @Override
    public void PortConnecting(String s) {

    }

    @Override
    public void PortClosing(String s) {

    }

    @Override
    public void OutPutTags(Tag_Model tag_model) {
        if(tag_model._TID !=null && !"".equals(tag_model)){
            String tid = tag_model._TID;
            String userData = tag_model._UserData;
            System.out.println(tid + "____" + HexUtil.decodeHexStr(userData, CharsetUtil.CHARSET_GBK));
            //commonRedisTemplate.opsForValue().set(tid,tid,1, TimeUnit.HOURS);
        }
    }

    @Override
    public void OutPutTagsOver() {

    }

    @Override
    public void GPIControlMsg(GPI_Model gpi_model) {

    }
}
