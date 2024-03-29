using System;
using System.Collections;

namespace ComparableCar
{
    public class Car : IComparable
    {
        // Constant for maximum speed.
        public const int maxSpeed = 100;

        // Internal state data.
        private int currSpeed;
        private string petName;
        private int carID;

        #region Properties
        public int CurrSpeed
        {
            get
            {
                return currSpeed;
            }

            set
            {
                currSpeed = value;
            }
        }
        public int ID
        {
            get
            {
                return carID;
            }

            set
            {
                carID = value;
            }
        }
        public string PetName
        {
            get
            {
                return petName;
            }

            set
            {
                petName = value;
            }
        } 
        #endregion

        // Is the car still operational?
        private bool carIsDead;

        // A car has-a radio.
        private Radio theMusicBox = new Radio();

        public Car() { }
        public Car(string name, int currSp)
        {
            currSpeed = currSp;
            petName = name;
        }
        
        public Car(string name, int currSp, int id)
        {
            currSpeed = currSp;
            petName = name;
            carID = id;
        }

        public void CrankTunes(bool state)
        {
            // Delegate request to inner object.
            theMusicBox.TurnOn(state);
        }

        public void Accelerate(int delta)
        {
            // Bad param?
            if (delta < 0)
                throw new ArgumentOutOfRangeException("Speed must be greater than zero!");

            if (carIsDead)
                Console.WriteLine("{0} is out of order...", petName);
            else
            {
                currSpeed += delta;
                if (currSpeed >= maxSpeed)
                {
                    carIsDead = true;
                    currSpeed = 0;

                    CarIsDeadException ex = 
                        new CarIsDeadException(string.Format("{0} has overheated!", petName));
                    ex.HelpLink = "http://www.CarsRUs.com";

                    // Stuff in extra info about the error.
                    ex.Data.Add("TimeStamp", string.Format("The car exploded at {0}", DateTime.Now));
                    ex.Data.Add("Cause", "You have a lead foot.");
                    throw ex;
                }
                else
                    Console.WriteLine("=> CurrSpeed = {0}", currSpeed);
            }
        }

        // Property to return the SortByPetName comparer.
        public static IComparer SortByPetName
        { get { return (IComparer)new PetNameComparer(); } }

        #region IComparable Members

        int IComparable.CompareTo(object obj)
        {
            Car temp = (Car)obj;
            if (this.carID > temp.carID)
                return 1;
            if (this.carID < temp.carID)
                return -1;
            else
                return 0;
        }

        #endregion
    }
}

