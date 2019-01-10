package in.amant.opencrowdcommunication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.model.ChatData;
import in.amant.opencrowdcommunication.model.MessageData;
import in.amant.opencrowdcommunication.model.User;

public class ChatActivity extends BaseActivity {

    private ChatView mChatView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String myName;

    String eventNo;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intentTitle = getIntent();
        String funcTitle = intentTitle.getExtras().getString("funcTitle");
        setTitle(funcTitle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventNo = intentTitle.getExtras().getString("eventNo");

        //User id
        final int myId = 0;
        //User icon
        final Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name
        Intent intent = getIntent();
        String event = intent.getExtras().getString("event");
        SharedPreferences pref = getSharedPreferences(event, MODE_PRIVATE);
        String user = pref.getString("user", "");
        myName = user;

        final int yourId = 1;
//        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_1);
        final Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user);
        String yourName = "Emily";

        final User me = new User(myId, myName, myIcon);
        final User you = new User(yourId, yourName, yourIcon);

        mChatView = (ChatView)findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.cyan500));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("new message...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);
        float twelveDp = TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics() );
        mChatView.setMessageFontSize(twelveDp);

        SharedPreferences prefUser = getSharedPreferences("userId", MODE_PRIVATE);
        userId = prefUser.getInt("userId", 0);
        if(userId == 0) {
            userId = (int) (Math.random() * 1000000000) + 1;

            SharedPreferences.Editor editor = prefUser.edit();
            editor.putInt("userId", userId);
            editor.commit();
        }

        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(mChatView.getInputText())) {

                    Calendar cal = Calendar.getInstance();
//                Date date =  sdf.parse("2014-05-08");

//                cal.setTime(date);

                    //calendar -> String 변환
                    String strDate = sdf.format(cal.getTime());

                    // Message 자체를 바로 데이터베이스에 보내면 될듯
//                ChatData chatData = new ChatData(me.getName(), mChatView.getInputText());  // 유저 이름과 메세지로 chatData 만들기
                    // 임시방편으로...
                    MessageData messageData = new MessageData(me.getName(), userId, mChatView.getInputText(), true, true, strDate);  // 유저 이름과 메세지로 chatData 만들기
                    databaseReference.child("messageData").child(eventNo).push().setValue(messageData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기

                    //new message
                /*Message message = new Message.Builder()
                        .setUser(me)
                        .setRight(true)
                        .setText(mChatView.getInputText())
                        .hideIcon(true)
                        .build();*/
                    //Set to chat view
//                mChatView.send(message);
                    //Reset edit text
                    mChatView.setInputText("");
                    // TODO: 2018-10-05 내가 입력한 채팅 서버로 보내기

                    //Receive message
                /*final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRight(false)
                        .setText(ChatBot.INSTANCE.talk(me.getName(), message.getText()))
                        .build();*/

                    // This is a demo bot
                    // Return within 3 seconds
                /*int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);*/

                }

            }

        });

        databaseReference.child("messageData").child(eventNo).addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageData messageData = dataSnapshot.getValue(MessageData.class);  // chatData를 가져오고
//                adapter.add(chatData.getUserName() + ": " + chatData.getMessage());  // adapter에 추가합니다.

                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(sdf.parse(messageData.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //new message
                // TODO: 2018-10-09 데이터베이스에 있는 메세지 가져와서, 메세지 작성자 이름과 쉐어드프리퍼런스에 있는 myName과 비교한다. 비교 후 이름이 같으면 setRight(true)로 설정해주고, 다르면 setRight(false)로 설정한다.
                Message message = new Message.Builder()
//                        .setUser(new User( messageData.getUserName().equals(myName) ? myId : yourId , messageData.getUserName(), messageData.getUserName().equals(myName) ? myIcon : yourIcon ))
                        .setUser(new User( userId , messageData.getUserName(), yourIcon ))
                        .setRight( messageData.getUserID() == userId ? true : false )
                        .setText(messageData.getMessage())
                        .hideIcon( messageData.getUserID() == userId ? true : false )
//                        .hideIcon(true)
                        .setSendTime(cal)
                        .build();
                //Set to chat view
                mChatView.send(message);

                //Receive message
                /*final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRight(false)
                        .setText(ChatBot.INSTANCE.talk(me.getName(), message.getText()))
                        .build();

                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);*/

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
