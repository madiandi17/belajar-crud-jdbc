CREATE TABLE t_kontak (
id INT AUTO_INCREMENT,
nama VARCHAR(255) NOT NULL,
alamat VARCHAR(255) NOT NULL,
tanggal_lahir DATE,
jenis_kelamin VARCHAR(255) NOT NULL,
aktif BOOLEAN,
PRIMARY KEY(id)
)Engine= InnoDB;

INSERT INTO t_kontak(nama, alamat, tanggal_lahir, jenis_kelamin, aktif) VALUE
('Madi Andi', 'Jl.Madi Gosling', '1994-12-17', 'Pria',true),
('Dima Gosling', 'Jl.Dima Gosling', '1994-12-12','Wanita', true);

// 1- test save
KontakDao kd = new KontakDao();
Kontak k = new Kontak();
k.setNama("Nomi");
k.setAlamat("Jl.Melati");
k.setTanggalLahir(new Date());
k.setJenisKelamin(JenisKelamin.WANITA);
k.setAktif(Boolean.TRUE);
kd.simpan(k);
        
// 2 - test update        
KontakDao kd = new KontakDao();
Kontak k = new Kontak();
k.setNama("Nomi Ayu Setiani");
k.setAlamat("Jl.Surabaya");
k.setTanggalLahir(new Date());
k.setJenisKelamin(JenisKelamin.WANITA);
k.setAktif(Boolean.FALSE);
k.setId(3);
kd.update(k);
        
// 3 - test delete        
KontakDao kd = new KontakDao();
Kontak k = new Kontak();
k.setId(6);
kd.delete(k);
        
// 4 - test getByNama
KontakDao kd = new KontakDao();
List<Kontak> list = kd.getByNama("Ayu");
    for (Kontak kk : list) {
        System.out.println(kk.getId());
        System.out.println(kk.getNama());
    }   
        
// 5 - test getAll
KontakDao kd = new KontakDao();
List<Kontak> list = kd.cariSemua();
    for (Kontak kk : list) {
        System.out.println(kk.getId());
        System.out.println(kk.getNama());
    }
        

