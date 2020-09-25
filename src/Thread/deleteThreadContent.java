package Thread;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Daryliu
 * @Date: 2020/9/25 20:57
 * @param:定时删除指定的带内容目录（在指定的时间删除我们指定的目录）
 * @return:
 */
public class deleteThreadContent {
    public static void main(String[] args) throws ParseException {
        Timer t = new Timer();
        String s = "2020-9-26";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date d = sdf.parse(s);//解析时间
        t.schedule(new deleteFolder(),d);//在d这个时间删除demo这个文件中的东西
    }
}

class deleteFolder extends TimerTask {
    @Override
    public void run() {
        File file = new File("demo");//找到我们现在这个文件中demo目录
        deleteFolder(file);
    }

    public void deleteFolder(File folder) {
        File[] files = folder.listFiles();//列出要删除的demo目录文件
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                }else {
                    file.delete();
                }
            }
            folder.delete();//最后删除文件夹
        }
    }
}
