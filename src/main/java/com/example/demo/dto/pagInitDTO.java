package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class pagInitDTO {
    private List<questionDTO> questionDTO;
    private boolean backFirstPag; //返回首页
    private boolean nextPag;      //下一页
    private boolean backLastPag;    //最后一页
    private boolean endPag;         //上一页
    private List<Integer> pags=new ArrayList<>(); //所有页码
    private int pag; //当前页
    private int totalPag; //总的页数


    public void setParmInit(int allCount, int pag, int size) {
        this.pag=pag;
        if (allCount%size ==0){
            totalPag=allCount/size;
        }else {
            totalPag=(allCount/size)+1;
        }

        if (pag<0){
            pag=1;
        }else if (pag>totalPag){
            pag=totalPag;
        }

        pags.add(pag);
        for (int i = 1; i <=3 ; i++) {
            if (pag-i>0){
                pags.add(0,pag-i);
            }

            if (pag+i<=totalPag){
                pags.add(pag+i);
            }
        }

        //判断是否有上一页
        if (pag ==1){
            endPag=false;
        }else{
            endPag=true;
        }
        //判断是否有下一页
        if (pag==totalPag){
            nextPag=false;
        }else{
            nextPag=true;
        }

        //如果在第一页
        /*pags.contains(1) 当前列表是否有1下标*/
        if (pags.contains(1)){
            backFirstPag=false;
        }else{
            backFirstPag=true;
        }


        if (pags.contains(totalPag)){
            backLastPag=false;
        }else{
            backLastPag=true;
        }

        System.out.println(pags.toString());
    }
}
