package com.example.contacts_zumrad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.contacts_zumrad.Adapter.Contact
import com.example.contacts_zumrad.Adapter.ContactAdapter
import com.example.contacts_zumrad.OtherStuff.DBHelper
import com.example.contacts_zumrad.databinding.FragmentContactsBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentContacts : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    var contacts = mutableListOf<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())

        contacts = db.getContacts()

        if (contacts.isEmpty()){
            binding.box.visibility = View.VISIBLE
        }
        else{
            var adapter = ContactAdapter(contacts, object : ContactAdapter.ContactInterface{
                override fun onClick(contact: Contact) {
                    val bundle = bundleOf()
                    bundle.putSerializable("contact",contact)
                    findNavController().navigate(R.id.action_fragmentContacts_to_viewFragment,bundle)
                }

            })
            binding.contactRv.adapter = adapter
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentContacts_to_addContactFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search ->{
                    var filter = mutableListOf<Contact>()

                }
            }
            true
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentContacts().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}