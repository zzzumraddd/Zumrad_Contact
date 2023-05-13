package com.example.contacts_zumrad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.contacts_zumrad.Adapter.Contact
import com.example.contacts_zumrad.OtherStuff.DBHelper
import com.example.contacts_zumrad.databinding.FragmentViewBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())
        val contact = arguments?.getSerializable("contact") as Contact

        binding.name.text = contact.name
        binding.phone.text = contact.phone

        binding.delete.setOnClickListener {
//            var myDialog = Dialog()
//            var manager = parentFragmentManager
//            myDialog.show(manager,"myDialog")
            db.deleteContact(contact)
            findNavController().navigate(R.id.action_viewFragment_to_fragmentContacts)
        }

        binding.edit.setOnClickListener {

        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}