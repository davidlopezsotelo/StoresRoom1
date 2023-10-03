package com.davidlopez.stores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.util.query
import com.davidlopez.stores.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: NotasAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSave.setOnClickListener {

            //creamos la nota desde el editText
            val nota=NotasEntity(name = mBinding.etName.text.toString())

            //creamos un segundo hilo para la insercion de datos en la base de datos
            Thread {
                //hacemos que la nota creada se inserte en la base de datos
                NotasApp.db.notasDao().addNota(nota)
            }.start()
            mAdapter.add(nota)// añadimos la nota con el adaptador
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        mAdapter= NotasAdapter(mutableListOf(),this)
        mGridLayout= GridLayoutManager(this,2)//numero de elementos por columna
        getNotas()
        mBinding.reciclerView.apply {
            setHasFixedSize(true)
            layoutManager=mGridLayout
            adapter=mAdapter
        }
    }

    //funcion para llamar a la base de datos y consultar todas las notas:

    private fun getNotas(){

        //configuramos una cola "queue"para aceptar los tipos de datos
        val queue=LinkedBlockingQueue<MutableList<NotasEntity>>()

        //abrimos un segundo hilo para que la app no pete.
        Thread {
            val notas = NotasApp.db.notasDao().getAllNotas()// consultamos a la base de datos

            // añadimos las consultas a la cola
            queue.add(notas)
        }.start()

        //mostramos los resultados
        mAdapter.setNotas(queue.take())

    }
    /*
    * OnClickListener
    * */
    override fun onClick(notasEntity: NotasEntity) {

    }

    //actualizar registro
    override fun onFavoriteNota(notasEntity: NotasEntity) {
        notasEntity.isFaborite=!notasEntity.isFaborite

        val queue=LinkedBlockingQueue<NotasEntity>()

        //insertar actualizacion en base de datos
        Thread{
            NotasApp.db.notasDao().updateNota(notasEntity)
            queue.add(notasEntity)
        }.start()
        mAdapter.update(queue.take())
    }

    //borrar registro

    override fun onDeleteNota(notasEntity: NotasEntity) {

        val queue=LinkedBlockingQueue<NotasEntity>()

        Thread{
            NotasApp.db.notasDao().deleteAll(notasEntity)
            queue.add(notasEntity)
        }.start()
        mAdapter.delete(queue.take())
    }
}