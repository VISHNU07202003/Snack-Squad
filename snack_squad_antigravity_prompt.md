# 🛒 Snack Squad — Full-Stack E-Commerce App Prompt for Antigravity

> **Purpose:** This is a complete build specification for Antigravity (or any AI app builder) to generate a fully marketable, production-ready snack/food e-commerce Android application — covering every UX step from cold launch to post-checkout confirmation. Design and implement as a senior product engineer + UX designer would ship it.

---

## 🎯 Project Overview

**App Name:** Snack Squad  
**Platform:** Android (Jetpack Compose) — mobile-first, responsive  
**Backend:** Firebase (Auth, Firestore, Storage, Cloud Functions)  
**Design Language:** Warm, bold, playful-yet-premium food brand aesthetic. Think Oatly meets Graza — opinionated typography, generous whitespace, strong color moments, and micro-interactions that feel alive.  
**Core Persona:** A 22–35 year old snack-obsessed buyer who wants fast browsing, zero friction checkout, and a UI that sparks joy.

---

## 🏗️ Tech Stack (Enforce These)

| Layer | Technology |
|-------|-----------|
| UI | Jetpack Compose (Material 3) |
| State | ViewModel + StateFlow + UDF pattern |
| Auth | Firebase Authentication (Email/Password + Google Sign-In) |
| Database | Cloud Firestore (real-time sync) |
| Storage | Firebase Storage (product images) |
| Cart | Firestore-backed cart with local StateFlow cache |
| Payments | Simulated checkout flow (Stripe-ready architecture) |
| Navigation | Compose Navigation with deep link support |
| DI | Hilt |
| Image Loading | Coil |
| Testing | JUnit + Compose UI Test |

---

## 📱 Complete User Experience Flow

Build every screen listed below. Each screen must be **fully functional**, **animated**, and **pixel-polished**.

---

### SCREEN 1 — Splash / Cold Launch

**Goal:** Brand impression in under 1.5 seconds.

- Display animated Snack Squad logo with a staggered reveal (logo mark bounces in, wordmark fades up)
- Check auth state silently in background
- Route: if authenticated → Home; if not → Onboarding
- Duration: max 1.5s; skip on re-launch if recently active
- Background: brand gradient (warm amber → coral)
- No loading spinners — motion IS the loading indicator

---

### SCREEN 2 — Onboarding (3-step carousel)

**Goal:** Communicate value prop, hook the user before signup.

- 3 swipeable illustrated cards:
  1. "Snacks, curated for you" — bold hero illustration of snack spread
  2. "Lightning-fast delivery" — map animation with delivery dot pulsing
  3. "Build your squad order" — group cart concept illustration
- Bottom: pill-shaped page indicator dots (animated on swipe)
- CTA: "Get Started" button (full-width, bold, brand color)
- Skip link top-right for returning users
- Auto-advance on 4s timer unless user swipes
- Parallax depth effect on card illustrations during swipe

---

### SCREEN 3 — Authentication

**Goal:** Get users in with zero friction. Two paths: Sign Up and Log In.

**Sign Up tab:**
- Fields: Full Name, Email, Password, Confirm Password
- Real-time inline validation (green checkmark / red underline as user types)
- Password strength indicator bar (4 states: weak / fair / strong / very strong)
- "Show password" toggle eye icon
- Terms of Service checkbox with tappable link
- CTA: "Create Account" — disabled until all fields valid
- On success: animated confetti burst → route to Home

**Log In tab:**
- Fields: Email, Password
- "Forgot Password?" tappable link → opens bottom sheet with email reset flow
- Google Sign-In button (proper Google branding, OAuth flow)
- Biometric login prompt if returning user has fingerprint enabled
- Error states: shake animation on invalid credentials
- CTA: "Log In"

**Shared:**
- Tab switcher at top (Sign Up / Log In) with animated underline
- Keyboard-aware layout (fields scroll above keyboard)
- All Firebase Auth errors mapped to human-readable messages

---

### SCREEN 4 — Home / Discovery Feed

**Goal:** Immediately surface desire. Make browsing feel like scrolling a great food magazine.

**Top Bar:**
- Greeting: "Good morning, [First Name] 👋"
- Location chip: "Delivering to: [City]" (tappable to change)
- Cart icon with animated badge counter (springs when item added)
- Search icon

**Search Bar (persistent, below top bar):**
- Expandable with smooth animation
- Voice search button
- Recent searches as chips
- Real-time results appear below as user types (debounced 300ms)

**Category Row (horizontal scroll):**
- Emoji + label chips: 🍿 Popcorn · 🍫 Chocolate · 🥜 Nuts · 🧃 Drinks · 🍪 Cookies · 🌶️ Spicy · 🌿 Healthy
- Selected state: filled brand color with white label
- Tap to filter the grid below

**Featured Banner (auto-scrolling carousel):**
- 3 promotional banners with deep-link product/category routing
- Parallax scroll effect
- Timer dots at bottom

**Products Grid:**
- 2-column staggered grid (Compose LazyVerticalStaggeredGrid)
- Each card: product image (full-bleed top), name, weight/size, price, "Add to Cart" button
- Card entrance: fade + slide up staggered by index (first 6 cards animate on load)
- "Add to Cart" button: taps trigger an arc animation of a mini product thumbnail flying to cart icon
- Out-of-stock products show greyed overlay with "Notify Me" CTA
- Infinite scroll with pagination (load 20 at a time)

**Flash Deals Section:**
- Countdown timer (HH:MM:SS live countdown)
- Horizontal scroll row of discounted products
- Deal badge: red "SALE" + original price struck through

---

### SCREEN 5 — Search Results

**Goal:** Surface the right product instantly.

- Debounced real-time results (300ms)
- Filter bottom sheet: Category, Price Range slider, Dietary tags (Vegan, Gluten-Free, etc.), Rating
- Sort options: Relevance / Price Low–High / Price High–Low / Newest / Best Rated
- Empty state: illustrated "no snacks found" + suggested alternatives
- Voice search with animated waveform listening indicator
- Recent + popular searches shown before typing

---

### SCREEN 6 — Product Detail Page (PDP)

**Goal:** Drive add-to-cart through information, imagery, and social proof.

**Layout:**
- Full-bleed hero image (zoomable on pinch)
- Image gallery thumbnails row below hero
- Shared element transition from grid card to PDP (Compose sharedElement)

**Content (scrollable below image):**
- Product name (large, bold)
- Brand name (tappable → brand page)
- Star rating + "(142 reviews)" tappable
- Price (large) + per-unit price (small, muted)
- Weight/size selector: pill chips (e.g. 100g / 200g / 500g) — price updates on selection
- Quantity stepper: − [count] + with haptic feedback
- Description (collapsible "Read more")
- Nutritional info: expandable accordion with table
- Ingredients list (tappable allergens highlighted in amber)
- Tags: Vegan · Non-GMO · Gluten-Free (green pill chips)
- "Frequently Bought Together" horizontal scroll row

**Sticky Bottom Bar:**
- "Add to Cart" button (full-width, animated press state)
- Wishlist heart icon (toggle with animated fill)
- Share icon

**Reviews Section:**
- Average rating breakdown (5-bar histogram)
- Review cards: avatar, name, stars, date, text, helpful votes
- "Load more reviews" pagination
- Write Review CTA (opens bottom sheet)

---

### SCREEN 7 — Cart

**Goal:** Transparent, confident, zero-hesitation path to checkout.

**Empty State:**
- Illustrated empty cart with tagline "Your cart is hungry 🍿"
- CTA: "Start Shopping"

**Filled Cart:**
- List of cart items: image thumbnail, name, size, quantity stepper, line price, remove (swipe-to-delete with undo snackbar)
- Swipe-to-delete with animated collapse + undo snackbar (3s)
- Quantity updates debounced and synced to Firestore
- Promo code field: text input + "Apply" button, success/error inline states
- Order Summary card:
  - Subtotal
  - Delivery fee (or "FREE 🎉" if above threshold)
  - Discount (if promo applied, green)
  - Tax
  - **Total** (bold, large)
- Savings callout: "You're saving $X today!" in green banner
- CTA: "Proceed to Checkout" (full-width, brand color)

**Cross-Sell Row:**
- "You might also like" horizontal scroll with quick-add buttons

---

### SCREEN 8 — Checkout Flow (3 steps, linear progress)

**Step 1 — Delivery Address**
- Saved addresses list (radio select)
- "Add New Address" form: Street, Apt/Suite, City, State, ZIP, Label (Home/Work/Other)
- Google Maps address autocomplete
- Map preview thumbnail of selected address
- Delivery time estimate: "Arrives in 30–45 min"
- "Continue" CTA

**Step 2 — Payment**
- Saved payment methods (masked card number, card brand icon)
- "Add New Card" form: Card Number (auto-formatted), Expiry, CVV, Name on Card
- Credit card preview that updates live as user types (flip animation for CVV)
- Apple Pay / Google Pay buttons (with proper brand assets)
- Cash on Delivery option
- Security badge: "256-bit SSL encrypted"
- "Continue" CTA

**Step 3 — Order Review**
- Full order summary (items, address, payment method)
- Edit links back to each step
- Estimated delivery window
- Special instructions text field
- **"Place Order"** CTA — large, high-contrast, confident

**On "Place Order" tap:**
- Full-screen loading overlay with animated logo
- On success: route to Order Confirmation screen
- On failure: error bottom sheet with retry

---

### SCREEN 9 — Order Confirmation

**Goal:** Delight. Reinforce trust. Give clear next steps.

- Confetti particle burst on arrival (Compose Canvas animation)
- Large animated checkmark (draw-on stroke animation)
- "Order Confirmed! 🎉" headline
- Order number (tappable to copy)
- Estimated delivery time with timeline graphic:
  `Order Placed → Being Prepared → Out for Delivery → Delivered`
  (step 1 highlighted, animated pulse)
- Item summary (compact)
- "Track Order" primary CTA
- "Continue Shopping" secondary CTA
- Share order CTA (deep link)

---

### SCREEN 10 — Order Tracking

**Goal:** Reduce anxiety. Make waiting fun.

- Live status timeline with animated step progression
- Map view (optional: show delivery radius animation)
- Estimated arrival countdown
- Delivery person card: name, photo, rating, call/message buttons
- "Report Issue" link

---

### SCREEN 11 — Profile & Account

- Avatar (editable, camera/gallery picker)
- Display name + email
- Sections:
  - My Orders (list with status chips)
  - Saved Addresses
  - Payment Methods
  - Wishlist
  - Notifications preferences (toggle list)
  - Help & Support
  - About / Legal
  - Log Out (destructive, confirm bottom sheet)

---

### SCREEN 12 — Order History

- List of past orders: thumbnail collage, date, total, status chip
- Tap to expand: full order details
- "Reorder" button on each (adds all items back to cart)
- Filter: All / Delivered / Cancelled / In Progress

---

## 🎨 Design System (Enforce Globally)

### Color Palette
```
Primary:    #F57C1F  (Burnt Orange)
Secondary:  #1A1A2E  (Deep Navy)
Accent:     #FFD166  (Sunflower Yellow)
Surface:    #FAFAF5  (Warm Off-White)
Error:      #E63946
Success:    #2DC653
Text/Body:  #1A1A2E
Text/Muted: #6B7280
```

### Typography
- Display: `Playfair Display Bold` (headings, hero text)
- Body: `DM Sans Regular/Medium` (UI, descriptions)
- Mono: `JetBrains Mono` (order numbers, codes)
- Scale: 12 / 14 / 16 / 20 / 24 / 32 / 40 / 56sp

### Spacing
- Base unit: 4dp
- Component padding: 16dp / 24dp
- Section gaps: 32dp
- Card radius: 16dp (standard) / 24dp (hero cards)

### Motion Principles
- Duration: 200ms (micro) / 350ms (transitions) / 500ms (celebrations)
- Easing: FastOutSlowIn for enters, LinearOutSlowIn for exits
- Principles: physics-based springs for interactive elements, ease curves for page transitions
- Haptics: selection = light, add-to-cart = medium, checkout success = success pattern

### Elevation
- Cards: 2dp
- Bottom bars: 8dp
- Modals/Bottom sheets: 24dp

---

## 🔐 Data Architecture (Firestore)

```
/users/{uid}
  - name, email, photoUrl, createdAt

/users/{uid}/addresses/{id}
  - street, city, state, zip, label, isDefault

/users/{uid}/cart/{productId}
  - quantity, addedAt, selectedSize

/users/{uid}/orders/{orderId}
  - items[], total, address, paymentMethod, status, createdAt, estimatedDelivery

/products/{productId}
  - name, brand, description, price, images[], category, tags[], stock, rating, reviewCount

/products/{productId}/reviews/{reviewId}
  - userId, userName, rating, comment, createdAt

/categories/{id}
  - name, emoji, displayOrder
```

---

## ⚡ Performance Requirements

- Cold start to interactive: < 2 seconds
- Image loading: skeleton shimmer placeholders, progressive JPEG via Coil
- Firestore listeners: unsubscribe on screen exit, re-subscribe on resume
- Cart sync: optimistic UI updates (update local state instantly, sync to Firestore async)
- Lazy loading: all lists paginated (20 items/page)
- Offline: cart persists locally with Room cache, sync on reconnect
- 25% improvement in concurrent session responsiveness via efficient Firestore query structuring (compound indexes, denormalized cart totals)

---

## ♿ Accessibility

- All interactive elements: minimum 48×48dp touch target
- Content descriptions on all images
- Sufficient color contrast (WCAG AA minimum)
- Screen reader navigation order logical top-to-bottom
- Support dynamic font sizes (sp units throughout)
- Focus indicators visible in all states

---

## 📋 README File — Required Sections

Generate a `README.md` with these sections:

```markdown
# Snack Squad 🍿

## Overview
## Screenshots (placeholder grid)
## Features
## Tech Stack
## Architecture (MVVM + Clean Architecture diagram description)
## Getting Started
  ### Prerequisites
  ### Firebase Setup
  ### Running the App
## Project Structure
## User Flow
  ### Authentication Flow
  ### Shopping Flow
  ### Checkout Flow
## Data Models
## Performance Optimizations
## Testing
## Contributing
## License
```

---

## ✅ Quality Bar — Definition of Done

Before considering any screen complete, verify:

- [ ] All states implemented: empty, loading, error, success
- [ ] Animations present on all key interactions
- [ ] Keyboard handling correct (IME, scroll behavior)
- [ ] Dark mode supported (all colors via MaterialTheme)
- [ ] Error messages user-friendly (no raw Firebase errors)
- [ ] Haptic feedback on cart add, checkout success
- [ ] Back stack and navigation behavior correct
- [ ] No hardcoded strings (use strings.xml)
- [ ] No memory leaks (coroutine scopes properly cancelled)
- [ ] Compose previews provided for each screen

---

## 🚀 Stretch Features (Build If Time Allows)

- [ ] Loyalty points system (earn on every order)
- [ ] Referral code sharing
- [ ] AR product preview (view product "on your table" via ARCore)
- [ ] Live order tracking with real-time delivery person location
- [ ] Push notifications (Firebase Cloud Messaging) for order updates
- [ ] Smart reorder suggestions based on order history
- [ ] Social cart sharing (invite friends to a shared cart)

---

*Built with the UX standards and architecture patterns from Google Developers Externz — Snack Squad v2.0*
