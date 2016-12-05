package info.kimjihyok.new_york_times_client.util;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.PostItem;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class DebugUtil {

    /**
     * For testing to throw in dummy data for post list items
     * @return
     */
    public static List<PostItem> getDummyData() {
        List<PostItem> testingData = new ArrayList<>();

        Multimedia media1 = new Multimedia();
        media1.setUrl("https://static01.nyt.com/images/2016/12/04/world/DIPLO1/DIPLO1-thumbLarge.jpg");
        media1.setHeight(75);
        media1.setWidth(75);
        List<Multimedia> mediaList = new ArrayList<>();
        mediaList.add(media1);

        PostItem item1 = new PostItem();
        item1.setTitle("Dummy Data");
        item1.setMultimedia(mediaList);

        PostItem item2 = new PostItem();
        item2.setTitle("Dummy Data2");
        item2.setMultimedia(mediaList);

        PostItem item3 = new PostItem();
        item3.setTitle("Dummy Data3");
        item3.setMultimedia(mediaList);

        PostItem item4 = new PostItem();
        item4.setTitle("Dummy Data4");
        item4.setMultimedia(mediaList);

        PostItem item5 = new PostItem();
        item5.setTitle("Dummy Data5");
        item5.setMultimedia(mediaList);


        testingData.add(item1);
        testingData.add(item2);
        testingData.add(item3);
        testingData.add(item4);
        testingData.add(item5);

        return testingData;
    }
}
