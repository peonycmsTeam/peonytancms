package com.peony.peonyfront.mediacount.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.mediacount.dao.MediaCountMapper;
import com.peony.peonyfront.mediacount.model.MediaCount;

@Service
public class MediaCountServiceImpl implements MediaCountService {

    @Resource
    private MediaCountMapper mediacountmapper;

    @Override
    public int insert(MediaCount mediaCount) {
        return this.mediacountmapper.insertSelective(mediaCount);
    }

    @Override
    public List<MediaCount> selectMediaCountByDate(String time) {
        return this.mediacountmapper.selectMediaCountByDate(time);
    }

    @Override
    public List<MediaCount> selectMediaoneByDate(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaoneByDate(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDate(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDate(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDate(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDate(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDate(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDate(mediacount);
    }

    @Override
    public List<MediaCount> selectWeixinByDates(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectWeixinByDates(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDateSheng(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDateSheng(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDateSheng(MediaCount medincount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDateSheng(medincount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDateSheng(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDateSheng(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaoneByDateZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaoneByDateZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaoneByDateYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaoneByDateYue(mediacount);
    }

    @Override
    public List<MediaCount> selectWeixinByDatesZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectWeixinByDatesZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectWeixinByDatesYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectWeixinByDatesYue(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDateZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDateZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDateZhou(MediaCount medincount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDateZhou(medincount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDateZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDateZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDateYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDateZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDateYue(MediaCount medincount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDateYue(medincount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDateYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDateYue(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDateShengZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDateShengZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDateShengZhou(MediaCount medincount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDateShengZhou(medincount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDateShengZhou(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDateShengZhou(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaByDateShengYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaByDateShengYue(mediacount);
    }

    @Override
    public List<MediaCount> selectMediaBbsByDateShengYue(MediaCount medincount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBbsByDateShengYue(medincount);
    }

    @Override
    public List<MediaCount> selectMediaBlogByDateShengYue(MediaCount mediacount) {
        // TODO Auto-generated method stub
        return this.mediacountmapper.selectMediaBlogByDateShengYue(mediacount);
    }

}
