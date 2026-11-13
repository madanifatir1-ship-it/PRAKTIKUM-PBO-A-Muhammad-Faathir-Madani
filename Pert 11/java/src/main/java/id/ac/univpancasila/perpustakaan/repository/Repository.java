package id.ac.univpancasila.perpustakaan.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Repository<T> {
    private final Map<String, T> data = new LinkedHashMap<>();
    private final Function<T, String> pengambilKunci;

    public Repository(Function<T, String> pengambilKunci) {
        this.pengambilKunci = pengambilKunci;
    }

    public void simpan(T item) {
        data.put(pengambilKunci.apply(item), item);
    }

    public Optional<T> cariById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<T> cariSemua() {
        return List.copyOf(data.values());
    }

    public boolean hapus(String id) {
        return data.remove(id) != null;
    }

    public int jumlah() {
        return data.size();
    }
}
