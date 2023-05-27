package cz.tmap.converterApp.fragmentsAndActivities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import cz.tmap.converterApp.calcAndConvFunctions.Conversion
import cz.tmap.converter.R
import cz.tmap.converterApp.database.AppDb
import cz.tmap.converterApp.database.ConversionDB
import java.sql.Timestamp

class ConvActivity : AppCompatActivity() {

    private lateinit var shortenedUnits: Array<String>
    private lateinit var fullUnits: Array<String>
    private lateinit var defHint: String
    private lateinit var defUnit: String
    private lateinit var title: String
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_conv)
        intent.getStringExtra("convType")?.let { setupUnits(it) }
        val fromTextViewLayout = findViewById<TextInputLayout>(R.id.edit_text_from_layout)
        val toTextViewLayout = findViewById<TextInputLayout>(R.id.edit_text_to_layout)
        fromTextViewLayout.hint = defUnit
        toTextViewLayout.hint = defUnit
        val fromTextView: TextView = fromTextViewLayout.findViewById(R.id.edit_text_from)
        val toTextView: TextView = toTextViewLayout.findViewById(R.id.edit_text_to)
        val unitFrom = findViewById<Button>(R.id.unit_from)
        val unitTo = findViewById<Button>(R.id.unit_to)
        unitFrom.text = defHint
        unitTo.text = defHint
        val windowFrom = createPopupWindow(isFromPopup = true)
        val windowTo = createPopupWindow(isFromPopup = false)
        makeMenusFunctions(windowFrom, windowTo)
        textFieldDisableInput()
        findViewById<Button>(R.id.button_zero_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}0"
        }
        findViewById<Button>(R.id.button_one_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}1"
        }
        findViewById<Button>(R.id.button_two_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}2"
        }
        findViewById<Button>(R.id.button_three_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}3"
        }
        findViewById<Button>(R.id.button_four_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}4"
        }
        findViewById<Button>(R.id.button_five_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}5"
        }
        findViewById<Button>(R.id.button_six_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}6"
        }
        findViewById<Button>(R.id.button_seven_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}7"
        }
        findViewById<Button>(R.id.button_eight_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}8"
        }
        findViewById<Button>(R.id.button_nine_len).setOnClickListener {
            fromTextView.text = "${fromTextView.text}9"
        }
        findViewById<Button>(R.id.button_dot_len).setOnClickListener {
            if (fromTextView.text.contains(".")) {
            } else if (fromTextView.text.isEmpty()) {
                fromTextView.text = "0."
            } else fromTextView.text = "${fromTextView.text}."
        }
        findViewById<Button>(R.id.button_clear_len).setOnClickListener {
            fromTextView.text = ""
            toTextView.text = ""
        }
        findViewById<Button>(R.id.button_remove_len).setOnClickListener {
            var expr = ""
            if (fromTextView.text.isNotEmpty()) {
                expr = fromTextView.text.subSequence(0, fromTextView.text.length - 1).toString()
            }
            fromTextView.text = expr
        }
        val plusMinusButton = findViewById<Button>(R.id.button_plus_minus)
        if (plusMinusButton.isVisible) {
            plusMinusButton.setOnClickListener {
                if (fromTextView.text.contains("-")) {
                    fromTextView.text = fromTextView.text.substring(1)
                } else if (fromTextView.text != "0") {
                    fromTextView.text = "-${fromTextView.text}"
                }
            }
        }
        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.button_convert).setOnClickListener {
            val conv = Conversion()
            try {
                when (type.lowercase()) {
                    resources.getString(R.string.area_title).lowercase() -> {
                        val res = conv.area(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.data_title).lowercase() -> {
                        val res = conv.data(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.length_title).lowercase() -> {
                        val res = conv.length(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.mass_title).lowercase() -> {
                        val res = conv.mass(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.speed_title).lowercase() -> {
                        val res = conv.speed(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.time_title).lowercase() -> {
                        val res = conv.time(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.volume_title).lowercase() -> {
                        val res = conv.volume(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.energy_title).lowercase() -> {
                        val res = conv.energy(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.power_title).lowercase() -> {
                        val res = conv.power(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.pressure_title).lowercase() -> {
                        val res = conv.pressure(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.frequency_title).lowercase() -> {
                        val res = conv.frequency(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.angle_title).lowercase() -> {
                        val res = conv.angle(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.density_title).lowercase() -> {
                        val res = conv.density(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.data_transfer_title).lowercase() -> {
                        val res = conv.dataTransfer(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                    resources.getString(R.string.temperature_title).lowercase() -> {
                        val res = conv.temperature(
                            fromTextView.text.toString(),
                            unitFrom.text.toString(),
                            unitTo.text.toString()
                        )
                        toTextView.text = res
                    }
                }
                val db = AppDb.getDatabase(baseContext)
                val dao = db.conversionDao()
                val convDB = ConversionDB(
                    type = type,
                    fromLabel = fromTextViewLayout.hint.toString(),
                    fromNumber = fromTextView.text.toString(),
                    fromUnit = unitFrom.text.toString(),
                    toLabel = toTextViewLayout.hint.toString(),
                    toNumber = toTextView.text.toString(),
                    toUnit = unitTo.text.toString(),
                    timestamp = Timestamp(System.currentTimeMillis())
                )
                dao.insert(convDB)
                db.close()
            } catch (_: Exception) {
            }
        }
    }

    private fun setupUnits(typeOfConversion: String) {
        when (typeOfConversion.lowercase()) {
            resources.getString(R.string.area_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.area_units)
                fullUnits = resources.getStringArray(R.array.area_units_full)
                title = resources.getString(R.string.area_title)
                type = resources.getString(R.string.area_title).lowercase()
            }
            resources.getString(R.string.data_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.data_units)
                fullUnits = resources.getStringArray(R.array.data_units_full)
                title = resources.getString(R.string.data_title)
                type = resources.getString(R.string.data_title).lowercase()
            }
            resources.getString(R.string.length_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.length_units)
                fullUnits = resources.getStringArray(R.array.length_units_full)
                title = resources.getString(R.string.length_title)
                type = resources.getString(R.string.length_title).lowercase()
            }
            resources.getString(R.string.mass_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.mass_units)
                fullUnits = resources.getStringArray(R.array.mass_units_full)
                title = resources.getString(R.string.mass_title)
                type = resources.getString(R.string.mass_title).lowercase()
            }
            resources.getString(R.string.speed_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.speed_units)
                fullUnits = resources.getStringArray(R.array.speed_units_full)
                title = resources.getString(R.string.speed_title)
                type = resources.getString(R.string.speed_title).lowercase()
            }
            resources.getString(R.string.time_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.time_units)
                fullUnits = resources.getStringArray(R.array.time_units_full)
                title = resources.getString(R.string.time_title)
                type = resources.getString(R.string.time_title).lowercase()
            }
            resources.getString(R.string.volume_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.volume_units)
                fullUnits = resources.getStringArray(R.array.volume_units_full)
                title = resources.getString(R.string.volume_title)
                type = resources.getString(R.string.volume_title).lowercase()
            }
            resources.getString(R.string.energy_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.energy_units)
                fullUnits = resources.getStringArray(R.array.energy_units_full)
                title = resources.getString(R.string.energy_title)
                type = resources.getString(R.string.energy_title).lowercase()
            }
            resources.getString(R.string.power_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.power_units)
                fullUnits = resources.getStringArray(R.array.power_units_full)
                title = resources.getString(R.string.power_title)
                type = resources.getString(R.string.power_title).lowercase()
            }
            resources.getString(R.string.pressure_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.pressure_units)
                fullUnits = resources.getStringArray(R.array.pressure_units_full)
                title = resources.getString(R.string.pressure_title)
                type = resources.getString(R.string.pressure_title).lowercase()
            }
            resources.getString(R.string.frequency_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.frequency_units)
                fullUnits = resources.getStringArray(R.array.frequency_units_full)
                title = resources.getString(R.string.frequency_title)
                type = resources.getString(R.string.frequency_title).lowercase()
            }
            resources.getString(R.string.angle_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.angle_units)
                fullUnits = resources.getStringArray(R.array.angle_units_full)
                title = resources.getString(R.string.angle_title)
                type = resources.getString(R.string.angle_title).lowercase()
            }
            resources.getString(R.string.density_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.density_units)
                fullUnits = resources.getStringArray(R.array.density_units_full)
                title = resources.getString(R.string.density_title)
                type = resources.getString(R.string.density_title).lowercase()
            }
            resources.getString(R.string.data_transfer_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.data_transfer_units)
                fullUnits = resources.getStringArray(R.array.data_transfer_units_full)
                title = resources.getString(R.string.data_transfer_title)
                type = resources.getString(R.string.data_transfer_title).lowercase()
            }
            resources.getString(R.string.temperature_title).lowercase() -> {
                shortenedUnits = resources.getStringArray(R.array.temperature_units)
                fullUnits = resources.getStringArray(R.array.temperature_units_full)
                title = resources.getString(R.string.temperature_title)
                type = resources.getString(R.string.temperature_title).lowercase()
                findViewById<TextView>(R.id.button_plus_minus).visibility = View.VISIBLE
            }
        }
        defHint = shortenedUnits[0]
        defUnit = fullUnits[0]
        findViewById<TextView>(R.id.desc_of_convert_no_negative).text = title
    }

    private fun makeMenusFunctions(popupWindowFrom: PopupWindow, popupWindowTo: PopupWindow) {
        val buttonFirst = findViewById<Button>(R.id.unit_from)
        buttonFirst.text = defHint
        val buttonSecond = findViewById<Button>(R.id.unit_to)
        buttonSecond.text = defHint
        buttonFirst.setOnClickListener {
            val location = IntArray(2)
            buttonFirst.getLocationOnScreen(location)
            popupWindowFrom.showAtLocation(
                buttonFirst,
                Gravity.NO_GRAVITY,
                location[0],
                location[1] + buttonSecond.height
            )
        }
        buttonSecond.setOnClickListener {
            val location = IntArray(2)
            buttonSecond.getLocationOnScreen(location)
            popupWindowTo.showAtLocation(
                buttonSecond,
                Gravity.NO_GRAVITY,
                location[0],
                location[1] + buttonSecond.height
            )
        }
    }

    private class CustomUnitAdapter(context: Context, data: Array<String>) :
        ArrayAdapter<String>(context, R.layout.list_item_layout, data) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.setTextColor(ContextCompat.getColor(context, R.color.white))
            return view
        }
    }

    private fun createListAdapter(): ArrayAdapter<String> {
        return CustomUnitAdapter(this, shortenedUnits)
    }

    private fun createPopupWindow(isFromPopup: Boolean): PopupWindow {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.units_layout, null)
        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val listView = popupView.findViewById<ListView>(R.id.units_list)
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedShortUnit = shortenedUnits[position]
            val selectedFullUnit = fullUnits[position]
            val unitButton =
                if (isFromPopup) findViewById(R.id.unit_from) else findViewById<Button>(R.id.unit_to)
            val unitHint: TextInputLayout =
                if (isFromPopup) findViewById(R.id.edit_text_from_layout) else findViewById(R.id.edit_text_to_layout)
            unitHint.hint = selectedFullUnit
            unitButton.text = selectedShortUnit
            popupWindow.dismiss()
        }
        listView.adapter = createListAdapter()
        popupWindow.isFocusable = true
        popupWindow.width = 800
        if (type == resources.getString(R.string.temperature_title)) {
            popupWindow.height = 400
        } else {
            popupWindow.height = 1000
        }
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true
        return popupWindow
    }


    private fun textFieldDisableInput() {
        val viewFrom = findViewById<TextView>(R.id.edit_text_from)
        val viewTo = findViewById<TextView>(R.id.edit_text_to)
        viewFrom.isFocusable = false
        viewFrom.isFocusableInTouchMode = false
        viewFrom.isClickable = false
        viewFrom.isLongClickable = false
        viewFrom.isCursorVisible = false
        viewTo.isFocusable = false
        viewTo.isFocusableInTouchMode = false
        viewTo.isClickable = false
        viewTo.isLongClickable = false
        viewTo.isCursorVisible = false
    }
}
