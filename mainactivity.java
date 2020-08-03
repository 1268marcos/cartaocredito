package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextCard;
    private Button mButtonVerify;

    public static String BandeiraCartao="";

    public static int somaDigitos(int numero) {
        if(numero < 9) {
            return numero;
        } else {
            return ( 1 + (numero % 10) );
        }
    }

    public static boolean checkValidade(String numeroCartao) {
        int somaPar=0;
        int somaImpar=0;
        int aux=0;

        //PARES
        for( int j=numeroCartao.length()-2; j>=0; j=j-2 ) {
            aux = Integer.parseInt(numeroCartao.charAt(j)+"");
            somaPar = somaPar +  somaDigitos(aux*2);
        }

        //IMPARES
        for( int i=numeroCartao.length()-1; i>=0; i=i-2) {
            aux = Integer.parseInt(numeroCartao.charAt(i)+"");
            somaImpar = somaImpar +  aux;
        }

        if( ( (somaPar+somaImpar)%10 ) == 0 ) {
            return true;
        } else {
            return false;
        }

    }

    public static String checkBandeiraCartao(String numero1IdEmissor, String numero2IdEmissor) {
        if (numero2IdEmissor.equals("37")) {
            return "AMERICAN EXPRESS";
        } else if (numero2IdEmissor.equals("35")) {
            return "JCB";  //JCB Japan Credit Bureau
        } else if (numero1IdEmissor.equals("4")) {
            return "VISA";
        } else if (numero1IdEmissor.equals("5")) {
            return "MASTER";
        } else if (numero1IdEmissor.equals("6")) {
            return "DISCOVER";
        } else {
            return "DESCONHECIDA";
        }
    }

    private void newVerify(){
        if(TextUtils.isEmpty(mEditTextCard.getText().toString())) {
            Toast.makeText(this, "Erro Preenchimento", Toast.LENGTH_SHORT).show();
            return;
        } else {

            if( mEditTextCard.getText().length() >= 13 && mEditTextCard.getText().length() <=16 ) {

                boolean flag = checkValidade(mEditTextCard.getText().toString());

                if( flag ) {

                    Toast.makeText(this, "Cartão Válido", Toast.LENGTH_SHORT).show();
                    BandeiraCartao = checkBandeiraCartao(mEditTextCard.getText().toString().substring(0,1), mEditTextCard.getText().toString().substring(0,2));
                    Toast.makeText(this, "Pertence a Bandeira " + BandeiraCartao, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, "Cartão Inválido", Toast.LENGTH_SHORT).show();
                }

            } else {

                Toast.makeText(this, "Número de cartão inválido", Toast.LENGTH_SHORT).show();

            }

        }

    }

    private void cleanInputs(){
        mEditTextCard.setText("");
    }

    public class NewVerifyClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            newVerify();
        }
    }

    public class CleanClick implements  View.OnClickListener{
        @Override
        public void onClick(View view){
            cleanInputs();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextCard = findViewById(R.id.editTextCard);

        mButtonVerify = findViewById(R.id.buttonVerify);

        mButtonVerify.setOnClickListener(new NewVerifyClick());
        
    }
}
