package augustana.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b;
    TextView t, t2;
    Long count;
    String str;
    public static DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("data");
    public static DatabaseReference moreDataRef = dataRef.child("moreData");
    public static DatabaseReference numDataRef = moreDataRef.child("numData");
    public static DatabaseReference textDataRef = moreDataRef.child("textData");
    public static String[] birds = {"Blue Jay", "Kiwi", "Golden Pheasant", "Philippine Eagle",
            "Peregrine Falcon", "Frigatebird", "Loon", "Merlin", "Nighthawk", "Oriole",
            "Puffin", "Quail", "Cassowary", "Emperor Penguin", "Andean Cock of the Rock", "Hoatzin", "Shoebill",
            "California Condor", "Arctic Tern", "Marabou Stork"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        t = findViewById(R.id.textBox);
        t2 = findViewById(R.id.textBox2);
        b.setOnClickListener(this);
        count = (long) 0;



    }

    @Override
    public void onClick(View v) {
        count++;
        updateDB(count);
        Random r = new Random();
        str = birds[r.nextInt(birds.length)];
        updateDBText(str);
    }

    public void updateDB(Long count) {
        numDataRef.setValue(count);
        numDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t.setText("Data from DB: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateDBText(String s) {
        textDataRef.setValue(s);
        textDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t2.setText("Random bird: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

