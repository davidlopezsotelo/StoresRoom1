package com.davidlopez.stores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.davidlopez.stores.databinding.FragmentEditNotaBinding
import com.google.android.material.snackbar.Snackbar

class EditContactFragment : Fragment() {


    private var mActivity:MainActivity?=null
    private lateinit var mBinding: FragmentEditNotaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding= FragmentEditNotaBinding.inflate(inflater,container,false)

        return mBinding.root
    }
    //creamos el menu
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * para que aparezca el action bar
        * ir a la carpeta res/values/themes/themes.xml
        * y borrar donde pone NoActionBar, en la linea 3
        * */
        mActivity=activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //configuramos el titulo
        mActivity?.supportActionBar?.title=getString(R.string.edit_title_add)//creamos el recurso

        //mostrar menu
        setHasOptionsMenu(true)
    }

    //sobreescribimos los metodos para el menu:
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save -> {
                Snackbar.make(mBinding.root,
                    getString(R.string.edit_message_save_sucess),
                    Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }

    //ciclo de vida del fragment

    override fun onDestroy() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title=getString(R.string.app_name)
        mActivity?.hideFab(true)

        setHasOptionsMenu(false)
        super.onDestroy()
    }
}