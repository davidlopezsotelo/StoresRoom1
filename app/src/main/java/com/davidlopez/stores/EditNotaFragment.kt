package com.davidlopez.stores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davidlopez.stores.databinding.FragmentEditNotaBinding

class EditNotaFragment : Fragment() {

    private lateinit var mBinding: FragmentEditNotaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding= FragmentEditNotaBinding.inflate(inflater,container,false)

        return mBinding.root
    }
}