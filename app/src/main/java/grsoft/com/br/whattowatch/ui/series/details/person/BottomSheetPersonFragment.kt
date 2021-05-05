package grsoft.com.br.whattowatch.ui.series.details.person

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.github.heyalex.bottomdrawer.BottomDrawerDialog
import com.github.heyalex.bottomdrawer.BottomDrawerFragment
import com.github.heyalex.handle.PlainHandleView
import com.github.heyalex.handle.PullHandleView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.ui.series.details.cast.CastFragment
import grsoft.com.br.whattowatch.ui.series.details.handle.RotateHandleView

class BottomSheetPersonFragment : BottomSheetDialogFragment() {

    private var mBottomSheetListener: BottomSheetListener?=null

    interface BottomSheetListener{
        fun onOptionClick(text: String)
    }

    companion object {
        fun newInstance(details: String) = BottomSheetPersonFragment().apply {
            arguments = Bundle().apply {
                putString("details", details)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_person, container, false)
    }
}