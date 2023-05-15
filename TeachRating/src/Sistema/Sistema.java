package Sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Profesores.Profesor;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Usuarios.Usuario;
import Valoracion.Comentario;
import Valoracion.Puntuacion;

public class Sistema implements Serializable{

    private static final long serialVersionUID = 1L;

    private static  Sistema instancia = null;

    private String usuarioAdministrador = "admin";

    private String contrasenaAdministrador = "contra";

    private boolean administrador = false;

    private Usuario usuarioActual = null;

    private  ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    private  ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();

    private  ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    private  ArrayList<Profesor> profesoresUsuarioActual = new ArrayList<Profesor>();

    public Sistema() {}

    public static  Sistema getInstance() { //Crea una instancia del sistema
        if(instancia == null) {
            try {
                instancia = new Sistema();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean registrarse(String mail, String contrasena) throws Exception {
        if (checkMail(mail)) {
            return false;
        }

        Usuario usuario = new Estudiante(mail, contrasena);

        if (usuario.validar(usuarioAdministrador, contrasenaAdministrador)){
            return false;
        }
        for (Usuario u: usuarios) {
            if (u.validar(usuario)) {return false;}
        }
        usuarios.add(usuario);
        usuarioActual = usuario;
        administrador = false;
        return true;
    }

    public boolean registrarse(Usuario usuario) throws Exception{
        if (usuario.validar(usuarioAdministrador, contrasenaAdministrador)){
            return false;
        }
        for (Usuario u: usuarios) {
            if (u.validar(usuario)) {return false;}
        }
        usuarios.add(usuario);
        usuarioActual = usuario;
        administrador = false;
        return true;
    }

    public boolean login(String usuario, String contrasena) throws Exception{
        if (usuario.equals(usuarioAdministrador) && contrasena.equals(contrasenaAdministrador)) {
            administrador = true;
            usuarioActual = null;
            return true;
        }
        for (Usuario u: usuarios) {
            if (u.validar(usuario, contrasena)) {
                usuarioActual = u;
                administrador = false;
                return true;
            }
        }
        return false;
    }

    public  boolean checkMail(String mail) {
        for (Usuario u: usuarios) {
            if (u instanceof Estudiante) {
                if (u.getCorreo().equals(mail)) {
                    return true;
                }
            }
        }
        return false;
    }

    public  ArrayList<Asignatura> getAsignaturas(){return this.asignaturas;}

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void addAsignatura(Estudiante usuario, String text) {
        for (Usuario user: usuarios) {
            if(user == usuario) {
                ((Estudiante) user).addAsignatura(new Asignatura(text));
            }
        }
    }

    public Estudiante getUsuario(String email) {
        for (Usuario user: usuarios) {
            if(user.getCorreo().equals(email)) {
                return (Estudiante) user;
            }
        }
        return null;
    }

    public boolean checkMailText(String m) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|es)$"; //el mail ha de contener letras/numeros/caracteres especiales, un @, letras/numeros/caracteres especiales y acabar en .com o .es
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(m);
        return matcher.matches();
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public Profesor findProfesor(String nombre, String apellido){
        for (Profesor p: this.profesores) {
            if(p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                return p;
            }
        }
        return null;
    }

    public void deleteProfesor(String nombre, String apellido) {
        profesores.remove(findProfesor(nombre, apellido));
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void puntuarProfesor(String nombre, String apellido, int puntuacion) {
        for (Profesor p: profesores) {
            if(p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                p.addPuntuacion((Estudiante) this.getUsuarioActual(), new Puntuacion(puntuacion));
            }
        }
    }

    public boolean checkComentario(String m) {
        String[] palabrasMalsonantes = {"Acojonar", "Afollonada", "Afollonado", "Agilipollada", "Agilipollado", "Agilipollar", "Alamierda","Amamonada","Amamonado, Amargada","Amargado","Anárquico","Anormal","Asesina","Asesinar","Asesino","Asquerosa","Asqueroso","Autoritaria","Autoritario","Autoritarismo","Badajo","Bastarda","Bastardo","Basura","Berzas","Berzotas","Bestia","Boba","Bobo","Bollera","Boluda","Boludez","Boludo","Borracha","Borrachaza","Borrachazo","Borrachera","Borracho","Borrachuza","Borrachuzo","Bronca","Bufón","Bufona","Bujarra","Bujarrilla","Bujarrón","Cabreada","Cabreado","Cabrear","Cabreo","Cabrón","Cabrona","Cabronada","Cabroncete","Caca","Cachonda","Cachondeo","Cachondo","Cagada","Cagado","Cagar","Cagarla","Cagarse","Cagoen","Cagón","Cagona","Calentorra","Calentorro","Calzonazo","Calzonazos","Camero","(Celadores)","Capulla","Capullo","Carajo","Carajota","Carajote","Carallo","Carnudo","Cascar","Cascarla","Casquete","Cateta","Cateto","Cazurra","Cazurro","Cencular","Cenutrio","Cepillar","Ceporra","Ceporro","Chapero","Chaquetera","Chaquetero","Chichi","Chingada","Chingar","Chivata","Chivato","Chocho","Chochona","Choriza","Chorizo","Chorra","Chorrada","Chorva","Chula","Chulilla","Chulillo","Chulita","Chulito","Chulo","Chuloputas","Chumino","Chúpame","Chúpamela","Chupópteros","Churra","Churrita","Chutarse","Chute","Cipote","Cipotón","Cojón","Cojones","Cojonudo","Comemierda","Comino","Coño","Cornuda","Cornudo","Correrse","Corrida","Corrupta","Corrupto","Cretina","Cretino","Cuerno","Cuesco","Culear","Culero","Cutre","Decapitar","Decojones","Degollar","Descojonarse","Descojone","Descojono","Desequilibrada","Desequilibrado","Desgraciada","Desgraciado","Déspota","Dictatorial","Doctorcilla","Doctorcillo","Doctorcita","Doctorcito","Drogata","Embustera","Embustero","Encabronar","Encubrimiento","Encular","Enganchada","Enganchado","Engañabobos","Engañar","Engaño","Enmascaramiento","Enmascarar","Envenenar","Escocida","Escocido","Estafa","Estafador","Estafadora","Estúpida","Estúpido","Facha","Falo","Farsante","Folla","Follada","Follado","Follador","Follador","Folladora","Follamos","Follando","Follar","Follarse","Follo","Follón","Follones","Friki","Frustrada","Frustrado","Fulana","Fulanita","Fulanito","Fulano","Furcia","Gallorda","Gamberra","Gamberro","Gañán","Gili","Gilipolla","Gilipollas","Gilipuertas","Gitaneo","Granuja","Greñudo","Guarra","Guarrita","Guarrito","Guarro","Guay","Hijadeputa","Hijaputa","Hijodeputa","Hijoputa","Hipócrita","Hostia","Huevo","Huevón","Huevona","Idiota","Ignorante","Imbécil","Impresentable","Jiñar","Jiñarse","Joder","Joderos","Jódete","Jodida","Jodido","Jodienda","Joputa","Ladrón","Ladrona","Lameculo","Litrona","Loca","Loco","Loquera","Loquero","Machacarla","Machorra","Mafia","Mafiosa","Mafioso","Majadera","Majadero","Malafolla","Malfolla","Malfollada","Malfollado","Malnacida","Malnacido","Malparida","Malparido","Mamada","Mámamela","Mamarla","Mamarracha","Mamarracho","Mameluco","Mamón","Mamona","Mamporrero","Mangante","Marica","Maricón","Maricona","Mariconazo","Marimacha","Marimacho","Mariposón","Masacre","Matanza","Matar","Matasanos","Mato","Matón","Mear","Mecorro","Medicucha","Medicucho","Mediquilla","Mediquillo","Mejiño","Melapelan","Memeo","Mentecata","Mentecato","Mentirosa","Mentiroso","Mierda","Minga","Miserable","Mocosa","Mocoso","Mogollón","Mojigata","Mojigato","Mojino","Mojón","Moña","Morralla","Mugra","Mugriente","Mugrosa","Mugroso","Nabo","Nalgas","Negligencia","Negligente","Negrata","Negrera","Negrero","Opresor","Opresora","Paja","Pajera","Pajero","Pajillera","Pajillero","Palurda","Palurdo","Pamplina","Panoli","Papanatas","Pasota","Payasa","Payaso","Pécora","Pedo","Pedorra","Pedorro","Pelandrusca","Pelandrusco","Pendeja","Pendejo","Peo","Perraso","Perversa","Perverso","Pesetera","Pesetero","Peta","Petarda","Petardo","Picha","Pichafloja","Pija","Pijar","Pijo","Pijotera","Pijotero","Pilila","Pinga","Piojosa","Piojoso","Pipote","Pirada","Pirado","Polla","Pollada","Pollón","Porcojones","Porculo","Porelculo","Porrera","Porrero","Porro","Pringada","Pringado","Proxeneta","Puerca","Puerco","Puñeta","Puñetera","Puñetero","Puta","Putada","Putero","Putilla","Putillo","Putita","Putito","Puto","Putón","Putona","Queosjodan","Querella","Rabo","Ramera","Ramero","Ratera","Ratero","Reinona","Reputa","Roña","Roñosa","Roñoso","Sabandija","Sangráis","Sangrantes","Sarasa","Sarna","Sarnosa","Sarnoso","Sinvergüenza","Soplaflautas","Soplapollas","Subidón","Subnormal","Sudaca","Tarada","Tarado","Taruga","Tarugo","Teta","Tete","Tocacojones","Tocapelotas","Tonta","Tonto","Torpe","Tortillera","Toto","Tragapollas","Tragasables","Trapicheo","Truño","Tusmuertos","Usurera","Usurero","Vividor","Vividora","Yoya","Zangana","Zangano","Zopenca","Zopenco","Zorra","Zorrilla","Zorro","Zorrón","Zorrona","Zurullo"};
        String regex = "\\b(" + String.join("|", palabrasMalsonantes) + ")\\b"; // expresion regular que coincide con cualquiera de las palabras malsonantes
        return (m.matches("(?i).*" + regex + ".*")); //true es que contiene palabras malsonantes, false no
    }

    public boolean insertarComentario(String nombre, String apellido, String m){
        if(!checkComentario(m)){
            for (Profesor p: profesores) {
                if(p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                    Comentario c = new Comentario(m);
                    p.addComentario(c);
                    return true;
                }
            }

        }
        return false;
    }

    public void setProfesoresUsuarioActual() {
        Estudiante estudiante = (Estudiante) this.usuarioActual;
        for(Profesor p : this.profesores) {
            for(Asignatura a : p.getAsignaturas()) {
                for(Asignatura a1 : estudiante.getAsignaturas()) {
                    if(estudiante.getProfesores().contains(p)) {
                        continue;
                    }
                    if(a.getNombre() == a1.getNombre()) {
                        estudiante.addProfesor(p);
                    }
                }
            }
        }
    }

    public Asignatura getAsignaturaPorNombre(String nombreAsignatura) {
        for(Asignatura a : this.asignaturas) {
            if(a.getNombre().equals(nombreAsignatura)) {
                return a;
            }
        }
        return null;
    }

    public boolean isAdmin(){
        return this.administrador;
    }

    public Asignatura findAsignatura(String nombre){
        for (Asignatura a: this.asignaturas) {
            if(a.getNombre().equals(nombre)) {
                return a;
            }
        }
        return null;
    }

    public void addProfesor(String nombre, String apellido, List<Asignatura> asignaturas2) {
        Profesor p = new Profesor(nombre, apellido, asignaturas2);
        this.profesores.add(p);
    }

}
