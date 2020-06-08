package com.example.gymapp;

import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<Training> trainings;
    private static  ArrayList<Plan> plans;

    public static  void  initTrainings(){
        if(null == trainings){
            trainings = new ArrayList<>();
        }

        Training pushUp = new Training(1,"Push up",
                "The push-up is a very common strength training exercise that is used by an array of individuals interested in improving their strength, including members of the military, bodybuilders and general fitness participants. Completing push-ups is effective at developing upper body strength. There are options that all strength levels can incorporate to make the exercise easier or more difficult.",
                "There are options for those who find push-ups too difficult or for those who need to increase their intensity. If you’re unable to complete a push-up, you can complete them from your knees instead of your toes. Get up into a push-up position and then lower your knees down to the floor so that your torso is still in a straight line with your thighs. You can also have your feet remain on the floor and place your hands on a bench, so that you’re at an incline position. If you want to increase the difficulty of the regular push-up, you can place your feet up onto a bench, which will increase the amount of body weight placed on your arms. To add an instability element to the exercise, place your feet on an exercise ball while completing push-ups.",
                "https://www.yourdictionary.com/images/definitions/lg/12981.push-up.jpg");
        trainings.add(pushUp);
        Training squat = new Training(2,"Squat",
                "A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up.",
                "The movement begins from a standing position. Weight is often added; typically in the form of a loaded barbell but dumbbells and kettlebells may also be used. When a barbell is used, it may be braced across the upper trapezius muscle, termed a high bar squat, or held lower across the rear deltoids, termed a low bar squat.",
                "https://media.self.com/photos/5ea9bc77bb9c6b75996c7e91/4:3/w_1024%2Cc_limit/squats_woman_exercise.jpg");
        trainings.add(squat);
        Training legpPress = new Training(3,"Leg Press",
                "The leg press is a popular piece of gym equipment that can help build key muscles in your legs. There are two types of leg press machines commonly found in gyms: the standard horizontal leg press and the 45-degree leg press that has a seat that reclines at an angle while your legs press upward in a diagonal direction.",
                "The leg press machine allows you to get the benefits of a barbell squat for developing the quadriceps. Secondarily, it develops the gluteus maximus, hamstrings, and calves.\n" +
                        "\n" +
                        "By varying your foot position you can emphasize different muscles. It builds strength in these muscles and you can use it to overcome imbalances, such as when runners have more developed hamstrings than quadriceps.\n" +
                        "\n",
                "https://images-na.ssl-images-amazon.com/images/I/71QjB5MjtBL._AC_SL1500_.jpg");
        trainings.add(legpPress);
        trainings.add(pushUp);
        trainings.add(squat);
        trainings.add(legpPress);
    }

    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    public static boolean addPlan(Plan plan){
        if(null == plans){
            plans = new ArrayList<>();
        }
        return plans.add(plan);
    }
}
