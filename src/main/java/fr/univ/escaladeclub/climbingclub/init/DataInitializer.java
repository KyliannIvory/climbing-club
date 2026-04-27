package fr.univ.escaladeclub.climbingclub.init;

import fr.univ.escaladeclub.climbingclub.entity.*;
import fr.univ.escaladeclub.climbingclub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Initializes sample data at application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final OutingRepository outingRepository;

    public DataInitializer(MemberRepository memberRepository,
                           CategoryRepository categoryRepository,
                           OutingRepository outingRepository) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.outingRepository = outingRepository;
    }

    @Override
    public void run(String... args) {

        // Create members
        Member kyliann = new Member(null, "Kouame","Kyliann", "kouamekyliann21@gmail.com", passwordEncoder.encode("azerty"),null );
        Member alice = new Member(null, "Smith", "Alice", "alice@club.com", passwordEncoder.encode("password123"), null);
        Member bob = new Member(null, "Johnson", "Bob", "bob@club.com", passwordEncoder.encode("securepass"), null);
        Member nathan = new Member(null, "Mufuta", "Nathan", "nathan@club.com", passwordEncoder.encode("test"), null);
        Member julie = new Member(null, "Dubois", "Julie", "julie@club.com", passwordEncoder.encode("climb123"), null);
        Member marc = new Member(null, "Moreau", "Marc", "marc@club.com", passwordEncoder.encode("alpin123"), null);
        memberRepository.saveAll(List.of(alice, bob, nathan, julie, marc, kyliann));

        // Create categories
        Category alpineRock = new Category(null, "Alpine Rock", null);
        Category sportClimbing = new Category(null, "Sport Climbing", null);
        Category bouldering = new Category(null, "Bouldering", null);
        Category iceClimbing = new Category(null, "Ice Climbing", null);
        categoryRepository.saveAll(List.of(alpineRock, sportClimbing, bouldering, iceClimbing));

        // Create outings
        Outing outing1 = new Outing(null, "Mont Blanc Adventure", "Climbing mixed terrain", "https://montblanc.com",
                LocalDate.of(2025, 6, 15), alpineRock, alice);
        Outing outing2 = new Outing(null, "Calanques Trip", "Beginner-friendly climbing", "https://calanques.com",
                LocalDate.of(2025, 4, 10), sportClimbing, bob);
        Outing outing3 = new Outing(null, "Fontainebleau Challenge", "Technical bouldering circuits", "https://fontainebleau.fr",
                LocalDate.of(2025, 7, 5), bouldering, nathan);
        Outing outing4 = new Outing(null, "Cascade de Glace", "Ice climbing on frozen waterfall", "https://glace-experience.com",
                LocalDate.of(2025, 1, 20), iceClimbing, julie);
        Outing outing5 = new Outing(null, "Gorges du Verdon", "Sport climbing above the river", "https://verdonclimb.com",
                LocalDate.of(2025, 5, 18), sportClimbing, marc);
        Outing outing6 = new Outing(null, "Matterhorn Ridge", "Advanced alpine route", "https://zermatt.ch",
                LocalDate.of(2025, 8, 10), alpineRock, bob);

        outingRepository.saveAll(List.of(outing1, outing2, outing3, outing4, outing5, outing6));
        List<Outing> newOutings = List.of(
                new Outing(null, "Chamonix Classic", "Famous alpine route", "https://chamonix.com",
                        LocalDate.of(2025, 5, 25), alpineRock, kyliann),

                new Outing(null, "Verdon Summer Lines", "Sunny sport routes", "https://verdon-summer.com",
                        LocalDate.of(2025, 6, 3), sportClimbing, marc),

                new Outing(null, "Bleau Blocks Fest", "Friendly bouldering event", "https://bleaublocks.com",
                        LocalDate.of(2025, 6, 7), bouldering, nathan),

                new Outing(null, "Ice Tunnel Adventure", "Unique ice climbing spot", "https://icetunnel.fr",
                        LocalDate.of(2025, 12, 1), iceClimbing, julie),

                new Outing(null, "Les Aiguilles Walk", "Moderate alpine climb", "https://aiguilles.com",
                        LocalDate.of(2025, 9, 12), alpineRock, alice),

                new Outing(null, "Céüse Rock Rally", "Top rope and lead climbing", "https://ceuse.com",
                        LocalDate.of(2025, 5, 30), sportClimbing, kyliann),

                new Outing(null, "Magic Wood Session", "Natural bouldering paradise", "https://magicwood.ch",
                        LocalDate.of(2025, 7, 20), bouldering, bob),

                new Outing(null, "Kander Ice Climb", "Steep icy adventure", "https://kander.com",
                        LocalDate.of(2025, 11, 15), iceClimbing, alice),

                new Outing(null, "Dolomites Traverse", "Classic alpine route", "https://dolomites.it",
                        LocalDate.of(2025, 9, 5), alpineRock, nathan),

                new Outing(null, "Buoux Sport Day", "Challenging sport routes", "https://buouxclimb.com",
                        LocalDate.of(2025, 6, 18), sportClimbing, bob),

                new Outing(null, "Annot Boulder Bash", "Technical sandstone problems", "https://annotboulder.fr",
                        LocalDate.of(2025, 8, 9), bouldering, julie),

                new Outing(null, "Alaska Ice Quest", "Remote ice experience", "https://alaskaice.com",
                        LocalDate.of(2025, 12, 20), iceClimbing, marc),

                new Outing(null, "Zinal Ridge Run", "High altitude rock route", "https://zinalridge.ch",
                        LocalDate.of(2025, 10, 2), alpineRock, kyliann),

                new Outing(null, "La Ciotat Lines", "Sea cliff sport climbing", "https://laciotat.com",
                        LocalDate.of(2025, 5, 22), sportClimbing, alice),

                new Outing(null, "Boulder World Games", "Friendly competition", "https://bouldergames.org",
                        LocalDate.of(2025, 9, 30), bouldering, nathan),

                new Outing(null, "Saas-Fee Glacier", "Ice climbing expedition", "https://saasfee.ch",
                        LocalDate.of(2025, 11, 2), iceClimbing, bob),

                new Outing(null, "Aiguille du Midi Climb", "Legendary alpine outing", "https://aiguilledumidi.com",
                        LocalDate.of(2025, 8, 24), alpineRock, julie),

                new Outing(null, "Verdon Overhangs", "High level sport climbing", "https://verdon-overhangs.com",
                        LocalDate.of(2025, 6, 12), sportClimbing, nathan),

                new Outing(null, "Albarracín Blocks", "Bouldering in Spain", "https://albarracinclimb.com",
                        LocalDate.of(2025, 10, 10), bouldering, alice),

                new Outing(null, "Glace de la Meije", "Technical ice route", "https://meije-glace.fr",
                        LocalDate.of(2025, 12, 8), iceClimbing, kyliann)
        );

        outingRepository.saveAll(newOutings);

        System.out.println("✔ Sample data loaded.");
    }
}