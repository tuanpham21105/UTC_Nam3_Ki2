# Tạo ContextMenu

## Tạo ContextMenu layout

## Đăng ký ContextMenu cho View
```
        registerForContextMenu(listView);
```

## Thêm ContextMenu vào MainActivity
```
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
```

## Xử lý chọn item trong ContextMenu
```
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        Sach a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemSua) {
        
            return true;
        }
        else if (item.getItemId() == R.id.menuItemXoa) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
```

## Tạo Floating Dialog
```
    void OpenFloatingDialog(KhachHang a) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete");
            builder.setMessage(String.format(
                    "Bạn có muốn xóa khách hàng với thông tin \n" +
                    "%s\n%s\n%s\n%s\n%.1f", a.getMa(), a.getHoTen(), a.getSoDienThoai(), a.getNgayDanhGia().toString(), a.tinhDiem())
            );

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DeleteItem(position);
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked No
                    dialog.dismiss();
                }
            });

            builder.show();
    }
```

## Hàm xóa dữ liệu trong Db và Adapter
```
    void DeleteItem(int position) {
        KhachHang a = myAdapter.getItem(position);

        myDb.delete(a.getMa());

        myAdapter.data.remove(position);

        myAdapter.notifyDataSetChanged();

        UpdateTrungBinh();
    }
```

