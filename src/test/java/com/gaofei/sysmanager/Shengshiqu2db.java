package com.gaofei.sysmanager;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Shengshiqu2db {
	public static void main(String[] args) {

		Map<String,Integer> mapx = new HashMap<>();


		List<Map<String,String>> list = new ArrayList<>();

		Map<String,String> henan1= new TreeMap<>();
		henan1.put("a","河南省");
		henan1.put("b","商丘市");
		henan1.put("c","梁园区");

		Map<String,String> hebei= new TreeMap<>();
		hebei.put("a","河北省");
		hebei.put("b","保定市");
		hebei.put("c","满城县");

		Map<String,String> henan2= new TreeMap<>();
		henan2.put("a","河南省");
		henan2.put("b","商丘市");
		henan2.put("c","夏邑县");

		Map<String,String> henan3= new TreeMap<>();
		henan3.put("a","河南省");
		henan3.put("b","郑州市");
		henan3.put("c","金水区");



		list.add(henan1);
		list.add(hebei);
		list.add(henan2);
		list.add(henan3);




		list.forEach(m->{
			//记录父id
			AtomicReference<Integer> parentId= new AtomicReference<>(0);
			//记录下标值
			AtomicInteger index= new AtomicInteger();
			m.forEach((k,v)->{
				//下标递增
				index.getAndIncrement();
				//判断是否已经插入
				if(mapx.containsKey(v)){
					//记录父元素的id值
					parentId.set(mapx.get(v));
				}else {
					//数据库返回的主键ID值，模拟主键
					Integer id = new Random().nextInt(100);
					//判断是否是第一次插入，如果是每次单独记录值
					if(index.intValue()==1){
						System.out.println("第一次插入省了"+v+"返回id"+id);
						mapx.put("one",id);
					}else if (index.intValue()==2){
						if(parentId.get().equals(0)) {
							System.out.println("第一次插入市了" + v + "返回@id=" + id + ",parentId=" + mapx.get("one"));
							mapx.put("two", id);
						}else{
							System.out.println("第一次插入市了" + v + "返回@@存储twox@id=" + id + ",parentId=" + parentId);
							mapx.put("twox",id);
						}
					}else if(index.intValue()==3){
						if(mapx.get("twox")!=null){
							System.out.println("第一次插入区了twox@" + v + "返回i=d" + id + ",parentId=" + mapx.get("twox"));
							mapx.put("twox",null);
						}else {
							if (mapx.get("two")!=null) {
								System.out.println("第一次插入区了two@" + v + "返回i=d" + id + ",parentId=" + mapx.get("two"));
								mapx.put("two", null);
							}else{
								System.out.println("第一次插入区了" + v + "返回i=d" + id + ",parentId=" + parentId);
							}
						}

					}
					//都存入到map集合缓存中，用于检测是否存入过数据
					mapx.put(v,id);

				}
			});
		});





	}
}
