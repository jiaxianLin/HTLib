package com.ht.htlibrary.template.bean;

/**
 * Created by rinkousen on 2017/8/31 0031.
 */

public class SectionTemplate extends BaseTemplate{

	/**
	 * 获取section嵌套等级
	 * @return
	 */
	public int getLevel(){
		int level = 0;
		SectionTemplate tempSection = this.sectionTemplate;
		while (tempSection != null){
			tempSection = tempSection.getSectionTemplate();
			level ++;
		}
		return level;
	}
}
