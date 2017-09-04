package com.ht.htlibrary.ui.activity.read;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 读卡接口，用于解耦，更改读卡设备方便
 */

public interface IRead {

	boolean onInitReader();

	void onReadCard(String cardno);

	void onReadCard(List<String> cardnos);

}
