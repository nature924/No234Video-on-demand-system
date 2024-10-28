
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 视频信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shipin")
public class ShipinController {
    private static final Logger logger = LoggerFactory.getLogger(ShipinController.class);

    private static final String TABLE_NAME = "shipin";

    @Autowired
    private ShipinService shipinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告
    @Autowired
    private ShipinCollectionService shipinCollectionService;//视频收藏
    @Autowired
    private ShipinLiuyanService shipinLiuyanService;//视频留言
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("shipinDeleteStart",1);params.put("shipinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = shipinService.queryPage(params);

        //字典表数据转换
        List<ShipinView> list =(List<ShipinView>)page.getList();
        for(ShipinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShipinEntity shipin = shipinService.selectById(id);
        if(shipin !=null){
            //entity转view
            ShipinView view = new ShipinView();
            BeanUtils.copyProperties( shipin , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShipinEntity> queryWrapper = new EntityWrapper<ShipinEntity>()
            .eq("shipin_name", shipin.getShipinName())
            .eq("shipin_video", shipin.getShipinVideo())
            .eq("zan_number", shipin.getZanNumber())
            .eq("cai_number", shipin.getCaiNumber())
            .eq("shipin_types", shipin.getShipinTypes())
            .eq("shipin_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShipinEntity shipinEntity = shipinService.selectOne(queryWrapper);
        if(shipinEntity==null){
            shipin.setShipinClicknum(1);
            shipin.setShipinDelete(1);
            shipin.setInsertTime(new Date());
            shipin.setCreateTime(new Date());
            shipinService.insert(shipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShipinEntity shipin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());
        ShipinEntity oldShipinEntity = shipinService.selectById(shipin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(shipin.getShipinPhoto()) || "null".equals(shipin.getShipinPhoto())){
                shipin.setShipinPhoto(null);
        }
        if("".equals(shipin.getShipinVideo()) || "null".equals(shipin.getShipinVideo())){
                shipin.setShipinVideo(null);
        }

            shipinService.updateById(shipin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShipinEntity> oldShipinList =shipinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ShipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShipinEntity shipinEntity = new ShipinEntity();
            shipinEntity.setId(id);
            shipinEntity.setShipinDelete(2);
            list.add(shipinEntity);
        }
        if(list != null && list.size() >0){
            shipinService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<ShipinEntity> shipinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShipinEntity shipinEntity = new ShipinEntity();
//                            shipinEntity.setShipinName(data.get(0));                    //视频标题 要改的
//                            shipinEntity.setShipinPhoto("");//详情和图片
//                            shipinEntity.setShipinVideo(data.get(0));                    //视频 要改的
//                            shipinEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            shipinEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            shipinEntity.setShipinTypes(Integer.valueOf(data.get(0)));   //视频类型 要改的
//                            shipinEntity.setShipinClicknum(Integer.valueOf(data.get(0)));   //视频热度 要改的
//                            shipinEntity.setShipinContent("");//详情和图片
//                            shipinEntity.setShipinDelete(1);//逻辑删除字段
//                            shipinEntity.setInsertTime(date);//时间
//                            shipinEntity.setCreateTime(date);//时间
                            shipinList.add(shipinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        shipinService.insertBatch(shipinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ShipinView> returnShipinViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("shipinYesnoTypes",2);
        PageUtils pageUtils = shipinCollectionService.queryPage(params1);
        List<ShipinCollectionView> collectionViewsList =(List<ShipinCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ShipinCollectionView collectionView:collectionViewsList){
            Integer shipinTypes = collectionView.getShipinTypes();
            if(typeMap.containsKey(shipinTypes)){
                typeMap.put(shipinTypes,typeMap.get(shipinTypes)+1);
            }else{
                typeMap.put(shipinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("shipinTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("shipinYesnoTypes",2);
            PageUtils pageUtils1 = shipinService.queryPage(params2);
            List<ShipinView> shipinViewList =(List<ShipinView>)pageUtils1.getList();
            returnShipinViewList.addAll(shipinViewList);
            if(returnShipinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("shipinYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = shipinService.queryPage(params);
        if(returnShipinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnShipinViewList.size();//要添加的数量
            List<ShipinView> shipinViewList =(List<ShipinView>)page.getList();
            for(ShipinView shipinView:shipinViewList){
                Boolean addFlag = true;
                for(ShipinView returnShipinView:returnShipinViewList){
                    if(returnShipinView.getId().intValue() ==shipinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnShipinViewList.add(shipinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnShipinViewList = returnShipinViewList.subList(0, limit);
        }

        for(ShipinView c:returnShipinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnShipinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shipinService.queryPage(params);

        //字典表数据转换
        List<ShipinView> list =(List<ShipinView>)page.getList();
        for(ShipinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShipinEntity shipin = shipinService.selectById(id);
            if(shipin !=null){

                //点击数量加1
                shipin.setShipinClicknum(shipin.getShipinClicknum()+1);
                shipinService.updateById(shipin);

                //entity转view
                ShipinView view = new ShipinView();
                BeanUtils.copyProperties( shipin , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());
        Wrapper<ShipinEntity> queryWrapper = new EntityWrapper<ShipinEntity>()
            .eq("shipin_name", shipin.getShipinName())
            .eq("shipin_video", shipin.getShipinVideo())
            .eq("zan_number", shipin.getZanNumber())
            .eq("cai_number", shipin.getCaiNumber())
            .eq("shipin_types", shipin.getShipinTypes())
            .eq("shipin_clicknum", shipin.getShipinClicknum())
            .eq("shipin_delete", shipin.getShipinDelete())
//            .notIn("shipin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShipinEntity shipinEntity = shipinService.selectOne(queryWrapper);
        if(shipinEntity==null){
                shipin.setZanNumber(1);
                shipin.setCaiNumber(1);
            shipin.setShipinClicknum(1);
            shipin.setShipinDelete(1);
            shipin.setInsertTime(new Date());
            shipin.setCreateTime(new Date());
        shipinService.insert(shipin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

