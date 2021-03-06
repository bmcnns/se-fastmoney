package ca.dal.cs.csci3130.fastmoney.models;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentStatus extends AppCompatActivity {
    TextView txtId, txtAmount, txtStatus;

    PaymentModel responseData;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment_status);
//
//        txtId = findViewById(R.id.txtId);
//        txtAmount = findViewById(R.id.txtAmount);
//        txtStatus = findViewById(R.id.txtStatus);
//
//        Intent intent = getIntent();
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson mGson = builder.create();
//
//        responseData = mGson.fromJson(intent.getStringExtra("PaymentDetails"), PaymentModel.class);
//
//       /* try {
//            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
//            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }*/
//
//        showDetails(intent.getStringExtra("Amount"));
//    }

    private void showDetails(String paymentAmount) {
        txtId.setText("ID -- "+responseData.getResponse().getId());
        txtStatus.setText("Status -- "+responseData.getResponse().getState());
        txtAmount.setText("Amount -- $" + paymentAmount);
    }
}
