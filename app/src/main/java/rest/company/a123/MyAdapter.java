package rest.company.a123;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;
    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model = mList.get(position);
        holder.Nazwa.setText(model.getNazwa());
        holder.Adres.setText(model.getAdres());
        holder.Telefon.setText(model.getTelefon());
        holder.GodzinyZ.setText(model.getGodzinyZ());
        holder.GodzinyO.setText(model.getGodzinyO());
        holder.Kuchnia.setText(model.getKuchnia());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{


        TextView Nazwa,Adres,Telefon,GodzinyO,GodzinyZ,Kuchnia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Nazwa = itemView.findViewById(R.id.Nazwa_text);
            Adres = itemView.findViewById(R.id.Adres_text);
            Telefon = itemView.findViewById(R.id.telefon_text);
            GodzinyO = itemView.findViewById(R.id.GodzO_text);
            GodzinyZ = itemView.findViewById(R.id.GodzZ_text);
            Kuchnia = itemView.findViewById(R.id.Kuchnia_text);
        }
    }
}
