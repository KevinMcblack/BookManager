package com.example.verge.bookmanager;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

//精选
public class StoreFragment extends Fragment {
    public static String[] details = {"《三国演义》描写的是从东汉末年到西晋初年之间近一百年的历史风云。全书反映了三国时代的政治军事斗争，反映了三国时代各类社会矛盾的渗透与转化，概括了这一时代的历史巨变，塑造了一批咤叱风云的英雄人物。在对三国历史的把握上，作者表现出明显的拥刘反曹倾向，以刘备集团作为描写的中心，对刘备集团的主要人物加以歌颂，对曹操则极力揭露鞭挞。今天我们对于作者的这种拥刘反曹的倾向应有辩证的认识，尊刘反曹是民间传说的主要倾向，在罗贯中时代隐含着人民对汉族复兴的希望。",
            "《图说天下：三国演义》描写了魏、蜀、吴三国的兴亡史。故事起自汉末黄中起义，终于西晋统一。在镇压农民起义的过程中，各路军阀拥兵自立，互相混战；曹操“挟天子以令诸侯”，削平北方，进军南方，此后，三国互相争战各有胜负，最后归于西晋。",
            "本书描写了从东汉末年到西晋初年之间近105年的历史风云，以描写战争为主，反映了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，反映了三国时代各类社会斗争与矛盾的转化，概括了这一时代的历史巨变，塑造了一批叱咤风云的三国英雄人物，在广阔的背景上，上演了一幕幕气势磅礴的战争场面。",
            "全书反映了三国时代错综复杂的政治军事斗争，概括了这一时代的历史风云，塑造了曹操、刘备、孙权、诸葛亮等一批英雄人物。本书描写了大大小小的战争，构思宏伟，手法巧妙。其中官渡之战、赤壁之战等战争的描写波澜壮阔，读来惊心动魄、荡气回肠、爱不释手。",
            "本书描写了东汉末年到西晋初年近一百年间的历史风云，着重以曹操、刘备、孙权为首的魏、蜀、吴三个政治、军事集团之间的矛盾和斗争为背景，展示出那个时代尖锐复杂又极具特色的政治军事冲突，反映出入民在动乱时代的痛苦和灾难，以及他们反对分裂战争，追求和平统一的愿望。",
            "全国21位特级教师倾情打造，首套最符合新课标精神名著。",
            "本书描写了从东汉末年到西晋初年之间近105年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一批叱咤风云的三国英雄人物。",
            "本书是根据三国时期的史实和民间传说创作而成的优秀历史小说。它描述了东汉末年到东晋建立百年间发生的重大历史事件。作者通过真实动人的故事，揭示了封建统治阶级内部的黑暗和腐朽，控诉了统治者的暴虐和丑恶。",
            "《三国演义》语言流畅，雅俗共赏，全书结构严谨，借鉴编年史的手法，将百年间错综复杂的历史事件和层出不穷的历史人物串联起来，叙述有条不紊，浑然一体，“温酒斩华雄”“过五关斩六将”“威震长坂桥”“单骑救主”“空城计”等故事更是广为流传，家喻户晓。",
            "在我国光辉烂灿的文学宝库中，明清小说占有十分重要的地位。明、清两朝是中国历史上民族斗争和阶级斗争逐渐激化和深化的时期，构成了这个社会政治的基本内容，也为小说的创作提供了丰富的社会源泉。",};
    public static String[] writers = {"罗贯中", "韩伟滨", "[明]罗贯中", "[明]罗贯中", "[明]罗贯中", "南京合谷科技信息技术有限公司", "萧枫", "［明］罗贯中", "罗贯中", "罗贯中",};
    public static String[] book_url = {
            "http://yuedu.baidu.com/ebook/bbc86252f5335a8103d2201e?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/dd1d833f79563c1ec5da71de?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/71e337f48662caaedd3383c4bb4cf7ec4afeb68f?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/7230b82482c4bb4cf7ec4afe04a1b0717fd5b3e7?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/ee9f520a15791711cc7931b765ce0508763275fb?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/aaf93d9e6edb6f1afe001f52?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/90764e8964ce0508763231126edb6f1aff00711c?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/def9cca729ea81c758f5f61fb7360b4c2e3f2abe?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/d25572f8cfc789eb162dc82d?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/dd640e485a8102d276a22f7b?fr=search&type=book",
    };
    public static String[] title = {
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义",
            "三国演义（上）",};
    public static String[] price = {"0.1",
            "7",
            "21",
            "6.99",
            "9.6",
            "3",
            "8.97",
            "5",
            "10.99",
            "8",};
    public static String[] tags = {
            "['三国演义','古典文学','小说','罗贯中','古典名著','中国','名著','历史','中国古典','四大名著']",
            "['三国演义','古典文学','小说','罗贯中','古典名著','中国','名著','历史','中国古典','四大名著']",
            "['']",
            "['小说','古典','名著','三国']",
            "null",
            "['小说','名著','中国古典','四大名著']",
            "null",
            "['']",
            "['小说','名著','中国古典','四大名著']",
            "['运筹帷幄','励志','三国时期','中国经典','三国','故事','文学','四大名著','明清小说','历史小说','呂布','明代','古代','儿童文学','经典推荐','古今地名','天书','曹操','世界名著','滚滚长江罗灌水','三国演义','小时候','未买','六角丛书','Romance','Jins','小说','古代文学','漫畫','文艺','历史地理','中华书局','老羅你真禍害','中国','经典名著','家有藏书','动漫','国学','二年级','少儿','中国文学','回忆','历史','海豚出版社','大陆','中国小说','漫画','中国名著','中国古典文学','谋略','Three','Fiction','李典','童年','罗贯中书','張遼','中国古典','小说闲看','故乡书架','研究','百看不厌','library','整理','剧本','中国画报','图画书','连环画','地图','章回体小说','文学及文艺评论','郑渊洁','通俗小说','明本','張飛','名家改编','罗贯中','本社图书','收藏','三国历史','中国古代文学','很久以前讀的','名著','樂進','文学经典','古典名著','小說','毛宗岗','藏书','陈友琴','古典文学','古典','古']"
    };
    public static String[] idd = {"bbc86252f5335a8103d2201e",
            "dd1d833f79563c1ec5da71de",
            "71e337f48662caaedd3383c4bb4cf7ec4afeb68f",
            "7230b82482c4bb4cf7ec4afe04a1b0717fd5b3e7",
            "ee9f520a15791711cc7931b765ce0508763275fb",
            "aaf93d9e6edb6f1afe001f52",
            "90764e8964ce0508763231126edb6f1aff00711c",
            "def9cca729ea81c758f5f61fb7360b4c2e3f2abe",
            "d25572f8cfc789eb162dc82d",
            "dd640e485a8102d276a22f7b",};
    public static String[] publishOrg = {"北京燕山出版社", "吉林出版集团有限责任公司", "中央编译出版社", "北方妇女儿童出版社", "华夏出版社", "山东美术出版社",
            "辽海出版社", "北方文艺出版社", "群言出版社", "北京燕山出版社"};
    public static String[] photo = {
            "http://hiphotos.baidu.com/doc/pic/item/8d5494eef01f3a29de01404d9a25bc315d607c6c.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/4ec2d5628535e5ddd960d8a674c6a7efce1b627e.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/4ec2d5628535e5ddcc19c19f7ac6a7efce1b62bc.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/eac4b74543a982263c711c518082b9014a90eb10.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/5fdf8db1cb134954cd260fea5c4e9258d1094a00.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/3812b31bb051f819a9c6e058dcb44aed2f73e7c5.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/8b82b9014a90f603f6cadfbd3312b31bb151edd6.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/a044ad345982b2b7c8bf479e3dadcbef76099b43.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/1c950a7b02087bf4072e6d34f5d3572c10dfcf28.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/574e9258d109b3dee04af78bc8bf6c81810a4c5f.jpg",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View storeLayout = inflater.inflate(R.layout.activity_book_store, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = storeLayout.findViewById(R.id.fresh);
        GridView gridView = storeLayout.findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(getContext(), photo));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(String.valueOf(id), "onItemClick:----------------");
                Intent intent = new Intent(getContext(), Book_details.class);
                intent.putExtra("details", details[(int) id]);
                intent.putExtra("photo", photo[(int) id]);
                intent.putExtra("title", title[(int) id]);
                intent.putExtra("writers", writers[(int) id]);
                intent.putExtra("book_url", book_url[(int) id]);
                intent.putExtra("price", price[(int) id]);
                intent.putExtra("tags", tags[(int) id]);
                intent.putExtra("publishOrg", publishOrg[(int) id]);
                intent.putExtra("id", idd[(int) id]);
                startActivity(intent);//启动Activity
            }
        });

        gridView.setAdapter(new ImageListAdapter(getContext(), eatFoodyImages));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        return storeLayout;
    }
}
