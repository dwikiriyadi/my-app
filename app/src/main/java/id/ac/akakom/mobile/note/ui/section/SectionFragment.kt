package id.ac.akakom.mobile.note.ui.section


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.databinding.FragmentSectionBinding
import id.ac.akakom.mobile.note.utility.ViewModelInjector
import id.ac.akakom.mobile.note.data.model.Section
import id.ac.akakom.mobile.note.databinding.DialogEditTextBinding
import id.ac.akakom.mobile.note.adapter.SectionAdapter

class SectionFragment : androidx.fragment.app.Fragment() {
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.section_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> {
                val builder = AlertDialog.Builder(context!!)
                val view = DialogEditTextBinding.inflate(layoutInflater)

                builder.setTitle(R.string.add_section)
                    .setView(view.root)
                    .setNegativeButton(R.string.cancel) { _, _->

                    }
                    .setPositiveButton(R.string.add) { _, _->
                        viewModel.insert(Section(title = view.editText.text.toString()))
                    }.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = SectionFragment()
    }

    private lateinit var binding: FragmentSectionBinding
    private lateinit var viewModel: SectionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        activity!!.title = resources.getString(R.string.app_name)

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)

        val factory = ViewModelInjector.provideSectionViewModelFactory(requireContext())

        viewModel = ViewModelProviders.of(this, factory).get(SectionViewModel::class.java)

        binding = FragmentSectionBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.all.observe(viewLifecycleOwner, Observer { sections ->
            if (sections != null && sections.isNotEmpty()) {
                binding.recyclerView.adapter = SectionAdapter(sections, viewModel)
                binding.message.visibility = View.GONE
                binding.sectionContainer.visibility = View.VISIBLE
            } else {
                binding.message.visibility = View.VISIBLE
                binding.sectionContainer.visibility = View.GONE
            }
        })

        return binding.root
    }
}
