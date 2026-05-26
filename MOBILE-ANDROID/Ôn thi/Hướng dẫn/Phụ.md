# Phụ

## Thanh Search

### Tạo thanh Search trong Activity
```
    EditText searchEt;
    void initSearch() {
        searchEt = findViewById(R.id.editTextSearch);
        
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                search();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }
```

### Thêm hàm tìm kiếm
```
    void search() {
        String keyword = searchEt.getText().toString().trim().toLowerCase();

        adapter.data.clear();

        if (keyword.isBlank()) {
            adapter.data.addAll(db.getAll());
        }
        else {
            for (VeTau v : db.getAll()) {
                if (v.gaDen.trim().toLowerCase().contains(keyword) || v.gaDi.trim().toLowerCase().contains(keyword)) {
                    adapter.data.add(v);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
```

## Toast
```
Toast.makeText(context, "WiFi OFF", Toast.LENGTH_SHORT).show();
```

## Broadcast Receiver

### Khởi tạo BroadcastReceiver
```
    BroadcastReceiver broadcastReceiver;
    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    Toast.makeText(MainActivity.this, "Wifi da duoc dieu chinh", Toast.LENGTH_LONG).show();
                }

                int state = intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_UNKNOWN
                );

                switch (state) {

                    case WifiManager.WIFI_STATE_ENABLED:
                        Toast.makeText(context,
                                "WiFi ON",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case WifiManager.WIFI_STATE_DISABLED:
                        Toast.makeText(context,
                                "WiFi OFF",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case WifiManager.WIFI_STATE_ENABLING:
                        break;

                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }
```

## Option Menu

### Thêm layout cho OptionMenu

### Thêm OptionMenu vào Activity
```
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
```

### Xử lý khi chọn Item trong OptionMenu
```    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuItemSort) {

            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
```

## Chuyển hướng sang SMS, Call
```
Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + a.soDienThoai));
Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + a.soDienThoai));
```
