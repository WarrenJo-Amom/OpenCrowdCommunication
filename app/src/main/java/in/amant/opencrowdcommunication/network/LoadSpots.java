package in.amant.opencrowdcommunication.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadSpots extends Thread {

    private String urlS;
    private String str;

    public LoadSpots(String urlS) {
        this.urlS = urlS;
    }

    public String getStr() {
        return str;
    }

    public void run() {

        StringBuilder jsonHtml = new StringBuilder();

        try {

            // 연결 url 설정
            URL url = new URL(urlS);

            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 연결되었으면.
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                // 연결되었음 코드가 리턴되면.
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                    for (; ; ) {

                        // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                        String line = br.readLine();

                        if (line == null) break;

                        // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                        jsonHtml.append(line + "\n");

                    }

                    br.close();

                }

                conn.disconnect();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        str = jsonHtml.toString();

    }

}