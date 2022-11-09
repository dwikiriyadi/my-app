package io.github.dwikiriyadi.app.features.note.section


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.dwikiriyadi.app.core.objects.entity.Section
import io.github.dwikiriyadi.app.core.viewmodels.section.SectionViewModel
import io.github.dwikiriyadi.app.features.note.components.adapter.SectionAdapter
import io.github.dwikiriyadi.app.R
import io.github.dwikiriyadi.app.databinding.DialogEditTextBinding
import io.github.dwikiriyadi.app.databinding.FragmentSectionBinding

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
