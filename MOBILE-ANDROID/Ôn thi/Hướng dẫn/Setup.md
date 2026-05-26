# Setup

## Tạo lớp GlobalUtils
```
public class GlobalUtils {
    public static int A = 46;
    public static int B = 21;

    public static float ceil(float value, int places) {

        double scale = Math.pow(10, places);

        return (float) (Math.ceil(value * scale) / scale);
    }
}
```

## Tạo lớp Object

## Tạo View trong Main activity layout

## Khởi tạo các view trong MainActivity
```
    TextView trungBinhTv;
    ListView listView;
    Button sortBtn;
    FloatingActionButton addBtn;
    void initView() {
        trungBinhTv = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);
        sortBtn = findViewById(R.id.buttonSort);
        addBtn = findViewById(R.id.buttonAdd);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);
    }

```

