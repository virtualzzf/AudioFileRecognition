import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

public class AudioToText {

	//设置APPID/AK/SK
    public static final String APP_ID = "10125090";
    public static final String API_KEY = "PoBxMWOSi7UIB7TQZBDLbe8o";
    public static final String SECRET_KEY = "WGntY14geRQAnnaS3OveKqm8DVrYey1t";
    private  AipSpeech client;
    private String fileUrl, format;
    private JSONObject res, res1, res2;
    
    public AudioToText(String url){
        // 初始化一个FaceClient
        client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        fileUrl=url;
    }
    
    public String convert(){
    	String text="文件格式错误";
    	if(fileUrl.endsWith(".pcm")){
    		format="pcm";
    	}else if(fileUrl.endsWith(".wav")){
    		format="wav";
    	}else if(fileUrl.endsWith(".opus")){
    		format="opus";
    	}else if(fileUrl.endsWith(".speex")){
    		format="speex";
    	}else if(fileUrl.endsWith(".amr")){
    		format="amr";
    	}
        res1 = client.asr(fileUrl, format, 8000, null);
        res2 = client.asr(fileUrl, format, 16000, null);
        res = res1;
        System.out.println(res1.toString(1));
        System.out.println(res2.toString(2));
        
        if(!res1.get("err_no").toString().equals("0") && res2.get("err_no").toString().equals("0")){
        	res=res2;
        	System.out.println("只有1正确");
        }
        else if(!res2.get("err_no").toString().equals("0") && res1.get("err_no").toString().equals("0")){ 
        	res=res1;
        	System.out.println("只有2正确");
        }
        else if(res1.get("err_no").toString().equals("0") && res2.get("err_no").toString().equals("0") &&
        	res1.get("result").toString().length()>=res2.get("result").toString().length()){
        	res=res1;
        	System.out.println("判断长短，1长");
        }
        else if(res1.get("err_no").toString().equals("0") && res2.get("err_no").toString().equals("0") &&
        	res2.get("result").toString().length()>res1.get("result").toString().length()){
        	res=res2;
        	System.out.println("判断长短，2长");
        }
        text = res.get("result").toString().replace("[","").replace("]", "").replace("\"","");
        return text;
    }


}
