<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="16dp">

<LinearLayout
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical"
android:padding="16dp">

<LinearLayout
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:background="#D3D3D3"
android:orientation="vertical">

<TextView
android:id="@+id/mulchTypeTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="25sp"
android:textStyle="bold"
android:layout_marginBottom="20dp"/>

<TextView
android:id="@+id/pricePerCubicYardTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="16sp"
android:layout_marginBottom="20dp" />

</LinearLayout>

<!-- Order Summary Title -->
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Order Summary"
android:textSize="25sp"
android:layout_marginTop="30dp"
android:textStyle="bold" />

<!-- Delivery details -->
<TextView
android:id="@+id/tvDeliveryDetails"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textStyle="bold"
android:textSize="20sp"
android:layout_marginTop="40dp"/>

<!-- Address -->
<TextView
android:id="@+id/tvAddress"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="20sp"
android:textStyle="bold"/>

<!-- Contracts -->
<TextView
android:id="@+id/tvContacts"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="20sp"
android:textStyle="bold" />

<!-- Costs -->
<androidx.constraintlayout.widget.ConstraintLayout
android:layout_width="match_parent"
android:layout_height="match_parent"
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto">

<TextView
android:id="@+id/tvCosts"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textStyle="bold"
android:textSize="20sp"
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>


<!-- Back and Place Order buttons -->
<LinearLayout
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:layout_marginTop="50dp"
android:gravity="center_vertical">

<Button
android:id="@+id/btnBack"
android:layout_width="140dp"
android:layout_height="wrap_content"
android:layout_weight="1"
android:text="Back" />

<View
android:layout_width="0dp"
android:layout_height="1dp"
android:layout_weight="1" />

<Button
android:id="@+id/btnPlaceOrder"
android:layout_width="140dp"
android:layout_height="wrap_content"
android:layout_weight="1"
android:gravity="center|center_vertical"
android:singleLine="true"
android:text="Place Order" />
</LinearLayout>

</LinearLayout>
</ScrollView>
