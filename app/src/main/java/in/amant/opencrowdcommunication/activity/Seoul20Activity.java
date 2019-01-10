package in.amant.opencrowdcommunication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import in.amant.opencrowdcommunication.R;

import static in.amant.opencrowdcommunication.activity.Seoul20Activity.Sort.CLOSED;
import static in.amant.opencrowdcommunication.activity.Seoul20Activity.Sort.OPEN;

public class Seoul20Activity extends AppCompatActivity {

    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;

    ImageView imageView13;

    ImageView imageView12;

    ImageView imageView14;

    enum Sort {
        OPEN, CLOSED
    }

    Sort sort = OPEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul20);
        setTitle("공간을 선택해주세요.");

        imageView14 = (ImageView) findViewById(R.id.imageView14);
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        imageView13 = (ImageView) findViewById(R.id.imageView13);
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sort == OPEN) {

                    imageView12.setImageResource(R.drawable.sort_up_gray);

                    imageView8.setVisibility(View.GONE);
                    imageView9.setVisibility(View.GONE);
                    imageView10.setVisibility(View.GONE);

                    sort = CLOSED;

                } else {

                    imageView12.setImageResource(R.drawable.sort_down_gray);

                    imageView8.setVisibility(View.VISIBLE);
                    imageView9.setVisibility(View.VISIBLE);
                    imageView10.setVisibility(View.VISIBLE);

                    sort = OPEN;

                }



            }
        });

        Glide.with(this).load(R.drawable.seoul20_park).into(imageView14);
        Glide.with(this).load(R.drawable.seoul_blue3).into(imageView8);
        Glide.with(this).load(R.drawable.seoul_brown3).into(imageView9);
        Glide.with(this).load(R.drawable.seoul_purple3).into(imageView10);
        Glide.with(this).load(R.drawable.seoul20_map).into(imageView13);

    }
}
