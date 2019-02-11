package id.ac.akakom.mobile.note.ui.page

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.databinding.FragmentPageBinding
import id.ac.akakom.mobile.note.utility.ViewModelInjector
import id.ac.akakom.mobile.note.adapter.PageAdapter
import id.ac.akakom.mobile.note.data.model.Page

class PageFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = PageFragment()
    }

    private lateinit var binding: FragmentPageBinding
    private lateinit var viewModel: PageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.title = arguments!!.getString("section_name")

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelInjector.providePageViewModelFactory(requireContext())

        viewModel = ViewModelProviders.of(this, factory).get(PageViewModel::class.java)

        binding = FragmentPageBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.all(arguments!!.getLong("id")).observe(viewLifecycleOwner, Observer { sections ->
            if (sections != null && sections.isNotEmpty()) {
                binding.recyclerView.adapter = PageAdapter(sections, viewModel)
                binding.message.visibility = View.GONE
                binding.pageContainer.visibility = View.VISIBLE
            } else {
                binding.message.visibility = View.VISIBLE
                binding.pageContainer.visibility = View.GONE
            }
        })

        binding.addButton.setOnClickListener {
            val bundle = bundleOf("event" to "add", "section_id" to arguments!!.getLong("id"))
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_pageFragment_to_writeFragment, bundle)
        }
        return binding.root
    }
}
