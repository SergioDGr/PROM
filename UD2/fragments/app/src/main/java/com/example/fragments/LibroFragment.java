package com.example.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LibroFragment extends Fragment {
    private LibroListener listener;
    private ListView lstLibro;

    private Libro[] libros = new Libro [] {
            new Libro ("El camino de los reyes", "El camino de los reyes es el primer" +
                    " volumen de «El Archivo de  las Tormentas», el resultado de más de una década de" +
                    " construcción y  escritura de universos, convertido en una obra maestra de la fantasía " +
                    " contemporánea en diez volúmenes. Con ella, Brandon Sanderson se postula  como el autor del " +
                    "género que más lectores está ganando en todo el mundo.", "9788466657662",
                    "Brandon Sanderson"),
            new Libro ("YO, ROBOT", "Publicada por primera vez en 1950, cuando la electrónica digital estaba en su infancia, Yo, robot resultó visionaria. Su influencia, de hecho, fue enorme, y no sólo en toda la ciencia ficción posterior, sino también en la propia ciencia de la robótica. Aquí formuló  Asimov por primera vez las tres leyes fundamentales de la robótica, de las que se valdría para plantear interrogantes que se adentran en el campo de la ética y de la psicología: ¿qué diferencia hay entre un robot inteligente y un ser humano?, ¿puede el creador de un robot predecir su comportamiento?, y ¿debe la lógica determinar lo que es mejor para la humanidad? \n" +
                    "\n" +
                    "A través de una serie de historias conectadas entre sí por el personaje de la robopsicóloga Susan Calvin, en las que aparecen todo tipo de máquinas inteligentes–robots que leen el pensamiento, robots que se vuelven locos, robots con sentido del humor o robots políticos–, Asimov inventa unos robots cada vez más perfectos, que llegan a convertirse en un desafío para sus creadores.\n" +
                    "\n" +
                    "Con todo, Yo, robot es uno de los pocos títulos de ciencia ficción que han superado con amplitud " +
                    "el círculo de lectores especialmente aficionados, entre los que a menudo se considera una obra cumbre.",
                    "9788435021340", "Isaac Asimov"),
            new Libro ("EL HOBBIT","Smaug parecía profundamente dormido cuando Bilbo espió una vez más desde la entrada. ¡Pero fingía! ¡Estaba vigilando la entrada del túnel!... Sacado de su cómodo agujero-hobbit por Gandalf y una banda de enanos, Bilbo se encuentra de pronto en medio de una conspiración que pretende apoderarse del tesoro de Smaug el Magnífico, un enorme y muy peligroso dragón...",
                    "9788445013946", "J.r.r. Tolkien"),
            new Libro ("DANZA DE DRAGONES", "La victoria de los leones Lannister ha dejado tras de sí un interminable reguero de sangre: lord Tywin yace enterrado, asesinado por mano de su propio hijo; la reina Cersei está encadenada y el pequeño rey Tommen ocupa un trono que podría matarlo. El destino de Poniente pende de un hilo.\n" +
                    "\n" +
                    "En el Muro, Jon Nieve se ve obligado a consolidar por la espada su rango como lord comandante de la Guardia de la Noche. Mientras, al otro lado del mar Angosto, Daenerys Targaryen, la Madre de Dragones, defiende su dominio contra hordas de enemigos tanto viejos como nuevos.\n" +
                    "\n" +
                    "Tras huir a las Ciudades Libres, el parricida Tyrion Lannister podría ser la clave para que la sangre del dragón, que nunca llegó a extinguirse por completo, resurja. No obstante, todo esfuerzo tal vez resulte ser en vano. Porque ahora, verdaderamente... se acerca el invierno.",
                    "9788401032639", "George R.R. Martin"),
            new Libro ("EL OJO DEL MUNDO", "La vida de Rand Al’Thor y sus amigos en Campo de Emond ha resultado bastante monótona hasta que una joven misteriosa llega al pueblo. Moraine, una maga capaz de encauzar el Poder Único, anuncia el despertar de una terrible amenaza.\n" +
                    "\n" +
                    " Esa misma noche, el pueblo se ve atacado por espantosos trollocs sedientos de sangre, unas bestias semihumanas que hasta entonces se habían considerado una leyenda. Mientras Campo de Emond soporta la ofensiva, Moraine y su guardián ayudan a Rand y a sus amigos a escapar.\n",
                    "9788445007006", "Robert Jordan"),
            new Libro ("LOS JUEGOS DEL HAMBRE", "En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los Juegos  del Hambre. Solo hay una regla: matar o morir.\n" +
                    " \n" +
                    "\n" +
                    "Cuando Katniss Everdeen, una joven de dieciseis años se presenta  voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende  como una condena a muerte. Sin embargo, Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza.",
                    "9788427202122", "Suzanne Collins")
    };

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lstLibro = (ListView) getView().findViewById(R.id.list);

        lstLibro.setAdapter(new AdaptadorLibro(this, libros));
        lstLibro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.onCorreoSeleccionado(
                            (Libro) lstLibro.getAdapter().getItem(position));
            }
        });
    }

    public void setCorreoListener (LibroListener listener){
        this.listener = listener;
    }

    class AdaptadorLibro extends ArrayAdapter<Libro> {
        Activity context;

        private Libro[] datos;

        AdaptadorLibro(Fragment context, Libro[] datos) {
            super(context.getActivity(), R.layout.list_fragment, datos);
            this.context = context.getActivity();
            this.datos = datos;
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            View item = convertView;
            ViewHolder holder;

            if(item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.list_item, null);

                holder = new ViewHolder();
                holder.isbn = (TextView)item.findViewById(R.id.txtISBN);
                holder.titulo = (TextView)item.findViewById(R.id.txtTitulo);
                holder.autor = (TextView) item.findViewById(R.id.txtAutor);

                item.setTag(holder);
            }else
                holder = (ViewHolder) item.getTag();

            holder.isbn.setText(datos[position].getIsbn());
            holder.titulo.setText(datos[position].getTitulo());
            holder.autor.setText(datos[position].getAutor());

            return (item);
        }

    }
    static class ViewHolder {
        TextView isbn;
        TextView titulo;
        TextView autor;
    }
}