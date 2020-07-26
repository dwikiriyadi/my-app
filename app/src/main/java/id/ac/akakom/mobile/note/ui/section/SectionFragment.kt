package id.ac.akakom.mobile.note.ui.section


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.adapter.SectionAdapter
import id.ac.akakom.mobile.note.data.model.Section
import id.ac.akakom.mobile.note.databinding.DialogEditTextBinding
import id.ac.akakom.mobile.note.databinding.FragmentSectionBinding
import id.ac.akakom.mobile.note.utility.toast

@AndroidEntryPoint
class SectionFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentSectionBinding
    private val viewModel by viewModels<SectionViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).apply {
            title = resources.getString(R.string.app_name)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSectionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            viewModel.all.observe(viewLifecycleOwner, Observer { sections ->
                if (sections != null && sections.isNotEmpty()) {
                    recyclerView.adapter = SectionAdapter(sections, viewModel)
                    message.visibility = View.GONE
                    sectionContainer.visibility = View.VISIBLE
                } else {
                    message.visibility = View.VISIBLE
                    sectionContainer.visibility = View.GONE
                }
            })

            viewModel.getAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.section_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val builder = AlertDialog.Builder(requireContext())
                val view = DialogEditTextBinding.inflate(layoutInflater)

                builder.setTitle(R.string.add_section)
                    .setView(view.root)
                    .setNegativeButton(R.string.cancel) { _, _ ->

                    }
                    .setPositiveButton(R.string.add) { _, _ ->
                        viewModel.insert(Section(title = view.editText.text.toString()))
                        viewModel.getAll()
                    }.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
